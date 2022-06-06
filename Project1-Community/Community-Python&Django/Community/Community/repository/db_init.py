import os
import json


class Topic():
    @staticmethod
    def setTopic(dic):
        '''
        to set variables of topic
        @Param dic, type dict, used to set topic variable
        @return, a string or None, string to indicate error, none to indicate no error
        '''
        if type(dic) is not dict:
            return None, 'dic must be dict type'
        if 'id' not in dic.keys():
            return None, 'id not in dic'
        try:
            int(dic['id'])
        except Exception as e:
            return None, str(e)
        if 'title' not in dic.keys():
            return None, 'title not in dic'
        try:
            str(dic['title'])
        except Exception as e:
            return None, str(e)
        if 'content' not in dic.keys():
            return None, 'content not in dic'
        try:
            str(dic['content'])
        except Exception as e:
            return None, str(e)
        if 'create_time' not in dic.keys():
            return None, 'create_time not in dic'
        try:
            int(dic['create_time'])
        except Exception as e:
            return None, str(e)
        return dic, None


class Post():
    @staticmethod
    def setPost(dic):
        '''
        to set variables of post
        @Param dic, type dict, used to set post variable
        @return, a string or None, string to indicate error, none to indicate no error
        '''
        if type(dic) is not dict:
            return None, 'dic must be dict type'
        if 'id' not in dic.keys():
            return None, 'id not in dic'
        try:
            int(dic['id'])
        except Exception as e:
            return None, str(e)
        if 'parent_id' not in dic.keys():
            return None, 'parent_id not in dic'
        try:
            int(dic['parent_id'])
        except Exception as e:
            return None, str(e)
        if 'content' not in dic.keys():
            return None, 'content not in dic'
        try:
            str(dic['content'])
        except Exception as e:
            return None, str(e)
        if 'create_time' not in dic.keys():
            return None, 'create_time not in dic'
        try:
            int(dic['create_time'])
        except Exception as e:
            return None, str(e)
        return dic, None


class Init():

    def __init__(self):
        self.topicIndexMap = {}
        self.postIndexMap = {}

    def InitTopicIndexMap(self):
        project = os.environ.get('PYCHARM_PROJECT')
        project = project + '\\Community\\data\\topic.txt'
        # print(project)
        with open(project, 'r', encoding="utf-8") as f:
            for i in f.readlines():
                if i[-1] == '\n':
                    i = i[:-1]
                # print(i,len(i))

                try:
                    dic = json.loads(i)
                    # print(dic)
                except Exception as e:
                    return None, str(e)

                topic = Topic()
                topic, err = topic.setTopic(dic)
                if err is not None:
                    return None, err
                self.topicIndexMap[topic['id']] = topic
        return self.topicIndexMap, err

    def InitPostIndexMap(self):
        project = os.environ.get('PYCHARM_PROJECT')
        # print(project)
        project = project + '\\Community\\data\\post.txt'
        # print(project)
        with open(project, 'r', encoding="utf-8") as f:
            for i in f.readlines():
                if i[-1] == '\n':
                    i = i[:-1]
                # print(i,len(i))

                try:
                    dic = json.loads(i)
                    # print(dic)
                except Exception as e:
                    return None, str(e)

                post, err = Post.setPost(dic)
                if err is not None:
                    return None, err
                if post['parent_id'] not in self.postIndexMap.keys():
                    self.postIndexMap[post['parent_id']] = []
                self.postIndexMap[post['parent_id']].append(post)
        return self.postIndexMap, err
