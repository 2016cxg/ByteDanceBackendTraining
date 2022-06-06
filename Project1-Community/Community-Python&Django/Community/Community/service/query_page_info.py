import json
import multiprocessing as mpro
from Community.repository.post import PostDAO
from Community.repository.topic import TopicDAO
from Community.service.lock import lock

readlock = lock.gen_rlock()

class PageInfo:

    def __init__(self, topic, postLst):
        self.Topic = topic
        self.PostLst = postLst

    def getTopic(self):
        return self.Topic

    def getPostLst(self):
        return self.PostLst

    def toJson(self):
        return json.dumps({
            'topic': self.Topic,
            'postLst': self.PostLst
        }, ensure_ascii = False )


class QueryPageInfoFlow:

    def __init__(self, id_):
        self.topicId = id_

        self.pageInfo = None
        self.Topic = None
        self.PostLst = None

        self.dic = mpro.Manager().dict()

        # self.readlock = lock.gen_rlock()
        # self.dic['readlock'] = lock.gen_wlock()
        # self.dic['lock'] = lock

    def Do(self):
        err = self.checkParam()
        if err is not None:
            return None, err
        self.prepareInfo()
        self.pageInfo, err = self.packPageInfo()
        if err is not None:
            return None, err
        return self.pageInfo, None

    def checkParam(self):
        if self.topicId < 0:
            return 'invalid topic id'
        return None

    def preparePost(self, topicId):
        global readlock
        postdao = PostDAO.getSingletonInstanceObject()

        # readlock = lock.gen_rlock()
        readlock.acquire()
        postlst, err = postdao.QueryPostsByParentId(topicId)
        # print('query id=', topicId, ' readlock=', id(readlock), ' lock=',id(lock))
        readlock.release()

        self.dic['postLst'] = postlst

    def prepareTopic(self, topicId):
        topicdao = TopicDAO.getSingletonInstanceObject()
        topic, err = topicdao.QueryTopicsById(topicId)
        self.dic['topic'] = topic

    def prepareInfo(self):
        print('query id=', self.topicId, ' readlock=', id(readlock), ' lock=', id(lock))

        propost = mpro.Process(target=self.preparePost, args=(self.topicId,))
        protopic = mpro.Process(target=self.prepareTopic, args=(self.topicId,))
        propost.start()
        protopic.start()
        propost.join()
        protopic.join()
        self.Topic = self.dic['topic']
        self.PostLst = self.dic['postLst']

    def packPageInfo(self):
        if self.Topic is None:
            return None, 'Get topic error'
        if self.PostLst is None:
            return None, 'Get PostLst error'
        return PageInfo(self.Topic, self.PostLst), None
