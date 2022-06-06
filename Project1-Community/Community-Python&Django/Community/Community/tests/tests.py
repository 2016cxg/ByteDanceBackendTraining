class Singleton:
    @classmethod
    def getSingletonInstanceObject(cls, *args, **kwargs):
        if not hasattr(cls, "ins"):
            insObject = cls(*args, **kwargs)
            setattr(cls, "ins", insObject)
        return getattr(cls, "ins")

    def __int__(self):
        self.abc = 1

    def getabc(self):
        return self.abc


if __name__ == "__main__":
    ins = Singleton.getSingletonInstanceObject()

    S
    print(ins.getabc())
