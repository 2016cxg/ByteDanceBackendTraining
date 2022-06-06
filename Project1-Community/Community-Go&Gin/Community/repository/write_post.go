package repository

//writePostIndexMap
//参数：
//		parentId，server.write_post传递，话题页标号，url给出，检查这个topicId，
//		time时间，server.write_post给出，根据当地时间设定
//		postId,根据当前PostIndexMap的最大值给出自增量
//		content，server.write_post从controller.write_post获得
//返回
//		err，是否成功写入
//操作
//		var postIndex []*post
//		postIndex := postIndexMap[parentId]
//		posttmp := &Post{postId, parentId, content, time}
//		postIndex = append(posttmp, postIndex)
//		postIndexMap[parentId] = postIndex

import (
	"bufio"
	"encoding/json"
	"fmt"
	"math"
	"os"
	"sync"
)

//type Post struct {
//	Id         int64  `json:"id"`
//	ParentId   int64  `json:"parent_id"`
//	Content    string `json:"content"`
//	CreateTime int64  `json:"create_time"`
//}

type PostIndexMapDao struct {
}

var (
	postIndexMapDao  *PostIndexMapDao
	postIndexMapOnce sync.Once
)

func NewPostIndexMapDaoInstance() *PostIndexMapDao {
	postIndexMapOnce.Do(
		func() {
			postIndexMapDao = &PostIndexMapDao{}
		})
	return postIndexMapDao
}

func (*PostIndexMapDao) MaxPostIndex() (maxId int64, err error) {
	maxPostId := 0
	for _, val := range postIndexMap {
		for _, item := range val {
			maxPostId = int(math.Max(float64(maxPostId), float64(item.Id)))
		}
	}
	return int64(maxPostId), nil
}

func (*PostIndexMapDao) MaxTopicIndex() (maxId int64, err error) {
	maxTopicId := 0
	for key, _ := range postIndexMap {
		maxTopicId = int(math.Max(float64(maxTopicId), float64(key)))
	}
	return int64(maxTopicId), nil
}

func (f *PostIndexMapDao) WritePostIndexMap(parentId int64, content string) (*Post, error) {
	maxPostId, _ := f.MaxPostIndex()
	postId := maxPostId + 1

	var createTime int64
	createTime = 1650437625

	post := Post{
		int64(postId),
		parentId,
		content,
		createTime,
	}
	posts, ok := postIndexMap[parentId]
	if !ok {
		postIndexMap[parentId] = []*Post{}
	}
	posts = append(posts, &post)
	postIndexMap[post.ParentId] = posts

	return &post, nil
}

type PostDBDao struct {
}

var (
	postDBDao     *PostDBDao
	postDBDaoOnce sync.Once
)

func NewPostDBDaoInstance() *PostDBDao {
	postDBDaoOnce.Do(
		func() {
			postDBDao = &PostDBDao{}
		})
	return postDBDao
}

func (f *PostDBDao) WritePostDB(post *Post) error {
	text, err := json.Marshal(post)
	if err != nil {
		return err
	}

	//fmt.Println(string(text))

	projectPath := os.Getenv("GO_PROJECT")
	fmt.Println(projectPath)
	filePath := projectPath + "/Community/data/post"
	open, err := os.OpenFile(filePath, os.O_WRONLY|os.O_APPEND, 0644)
	defer open.Close()
	if err != nil {
		return err
	}
	w := bufio.NewWriter(open)
	_, err = w.WriteString(string(text) + "\n")
	if err != nil {
		return err
	}
	//fmt.Printf("wrote %d bytes\n", n4)
	w.Flush()

	return nil
}
