


from Community.controller.query_page_info import Message
from Community.service.write_page_info import  WritePostFlow as ServiceWritePostFlow

class WritePostFlow:

    def __init__(self, pid, content):
        self.pid = pid
        self.content = content

    def DoWritePost(self):
        data, err = ServiceWritePostFlow().DoWritePost(self.pid, self.content)
        if err is not None:
            return Message(self.pid, err, None)
        return Message(self.pid, 'success', None)
