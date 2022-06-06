
from Community.repository.db_init import Init
import os

class TopicDAO():
    @classmethod
    def getSingletonInstanceObject(cls, *args, **kwargs):
        if not hasattr(cls, "ins"):
            insObject = cls(*args, **kwargs)
            setattr(cls, "ins", insObject)
        return getattr(cls, "ins")

    os.environ.setdefault('PYCHARM_PROJECT', r'/')
    topicIndexMap, err = Init().InitTopicIndexMap()

    def QueryTopicsById(self, id):
        # print(self.postIndexMap)
        if self.err != None:
            return None, self.err
        if type(id) is not int:
            return None, 'ParentId is not valid'
        if id not in self.topicIndexMap.keys():
            return None, 'invalid topic id'
        return self.topicIndexMap[id], None