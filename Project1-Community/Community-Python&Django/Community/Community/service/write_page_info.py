from Community.repository.post import PostDAO
from Community.service.lock import  lock


writelock = lock.gen_wlock()

class WritePostFlow:
    # def __init__(self):
    #     self.writelock = lock.gen_wlock()

    def checkParam(self, id):
        if type(id) is not str:
            return None, 'id is not int'
        try:
            id = int(id)
        except:
            return None, 'convert id to int error'
        if id < 0:
            return None, 'id is below 0'
        return id, None

    def writePost(self, id, content):
        postdao = PostDAO.getSingletonInstanceObject()
        data, err = postdao.DoWritePost(id, content)

        if err is not None:
            return None, err
        return None, None

    def DoWritePost(self, id_, content):
        global writelock

        id_, err = self.checkParam(id_)
        if err is not None:
            return None, err

        # writelock = lock.gen_wlock()
        writelock.acquire()
        data, err = self.writePost(id_, content)
        print('write id=', id_, ', content=', content, ' writelock=', id(writelock), ' lock=',id(lock))
        writelock.release()

        if err is not None:
            return None, err
        return None, None
