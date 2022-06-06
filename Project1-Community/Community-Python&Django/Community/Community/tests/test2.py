import multiprocessing
import time


class A(object):
    def __init__(self):
        self.a = None
        self.b = None
        # 初始化一个共享字典
        self.my_dict = multiprocessing.Manager().dict()

    def get_num_a(self):
        time.sleep(3)
        self.my_dict["a"] = 10

    def get_num_b(self):
        time.sleep(5)
        self.my_dict["b"] = 6

    def sum(self):
        self.a = self.my_dict["a"]
        self.b = self.my_dict["b"]
        print("a的值为:{}".format(self.a))
        print("b的值为:{}".format(self.b))
        ret = self.a + self.b
        return ret

    def run(self):
        p1 = multiprocessing.Process(target=self.get_num_a)
        p2 = multiprocessing.Process(target=self.get_num_b)
        p1.start()
        p2.start()
        p1.join()
        p2.join()
        print(self.sum())


if __name__ == '__main__':
    t1 = time.time()
    a = A()
    a.run()
    t2 = time.time()
    print("cost time :{}".format(t2 - t1))
