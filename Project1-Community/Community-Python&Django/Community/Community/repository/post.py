import json

from Community.repository.db_init import Init, Post
import os
import math


class PostDAO:
    @classmethod
    def getSingletonInstanceObject(cls, *args, **kwargs):
        if not hasattr(cls, "ins"):
            insObject = cls(*args, **kwargs)
            setattr(cls, "ins", insObject)
        return getattr(cls, "ins")

    os.environ.setdefault('PYCHARM_PROJECT', r'/')
    postIndexMap, err = Init().InitPostIndexMap()

    def QueryPostsByParentId(self, pid):
        # print(self.postIndexMap)
        if self.err is not None:
            return None, self.err
        if type(pid) is not int:
            return None, 'ParentId is not valid'
        if pid not in self.postIndexMap.keys():
            return None, 'invalid topic id'
        return self.postIndexMap[pid], None

    def MaxPostIndexByPid(self):
        # print(self.postIndexMap)

        lst = []
        for i in self.postIndexMap:
            for j in self.postIndexMap[i]:
                lst.append( j['id'] )
        return max(lst)

    def WritePostIndexMapByParentId(self, pid, content):
        if self.err != None:
            return None, self.err
        if type(pid) is not int:
            return None, 'ParentId is not valid'
        if pid not in self.postIndexMap.keys():
            return None, 'Invalid topic id'

        post, err = Post.setPost({
            'id': self.MaxPostIndexByPid() + 1,
            'parent_id': pid,
            'content': content,
            'create_time': 20000000000
        })
        if err is not None:
            return None, err

        self.postIndexMap[pid].append(post)

        # print('bbbbbbbbbb')
        return post, None

    def WritePostFile(self, post):
        if type(post) is not dict or \
                'id' not in post.keys() or \
                'parent_id' not in post.keys() or \
                'content' not in post.keys() or \
                'create_time' not in post.keys():
            return None, 'post is not valid dictionary'
        data = json.dumps(post)

        project = os.environ.get('PYCHARM_PROJECT')
        # print(project)
        project = project + '\\Community\\data\\post.txt'
        # print(project)
        with open(project, 'a', encoding="utf-8") as f:
            f.write(data + '\n')

        return None, None

    def DoWritePost(self, pid, content):
        # print(pid, content )
        post, err = self.WritePostIndexMapByParentId(pid, content)
        if err is not None:
            return None, err
        ret, err = self.WritePostFile(post)
        if err is not None:
            return None, err
        return None, None
