package main

import (
	"Community/controller"
	"Community/repository"
	"gopkg.in/gin-gonic/gin.v1"
	"os"
)

func main() {
	if err := Init("./data/"); err != nil {
		os.Exit(-1)
	}
	r := gin.Default()
	r.GET("/community/page/get/:id", func(c *gin.Context) {
		topicId := c.Param("id")                  //topic的索引
		data := controller.QueryPageInfo(topicId) //索引对应的数据
		c.JSON(200, data)                         //返回状态码和数据
	})

	r.POST("/community/page/post/:id", func(c *gin.Context) {
		topicId := c.Param("id") //gin获取url参数

		content := c.PostForm("content") //gin获取post请求表单参数

		data := controller.WritePost(topicId, content)

		c.JSON(200, data)

		//fmt.Println("id: ", topicId)
		//fmt.Println("content: ", content)
	})

	err := r.Run() //启动服务
	if err != nil {
		return
	}
}

func Init(filePath string) error {
	if err := repository.Init(filePath); err != nil {
		return err
	}
	return nil
}
