import json

from Community.service.query_page_info import QueryPageInfoFlow


# convert string to int
# send it to function QueryPgeInfoFlow

class Message:

    def __init__(self, topicId, msg, info):
        self.topicId = topicId
        self.msg = msg
        self.info = info

    def getInfo(self):
        return self.info

    def toJson(self):
        if self.info is not None:
            return json.dumps({
                'topicId': self.topicId,
                'msg': self.msg,
                'info': self.info.toJson()
            }, ensure_ascii= False )
        return json.dumps({
                'topicId': self.topicId,
                'msg': self.msg,
                'info': None
            }, ensure_ascii= False )


class QueryMessageFlow:

    def __init__(self, topicId):
        self.topicId = topicId
        self.msg = None
        self.info = None

        self.message = None

    def Do(self):
        try:
            self.topicId = int(self.topicId)
        except:
            return Message(self.topicId, "invalid topic id", None), 'fail'
        msg, err = QueryPageInfoFlow(self.topicId).Do()
        if err is not None:
            return Message(self.topicId, err, None), 'fail'
        return Message(self.topicId, None, msg), None
