package repository

import (
	"encoding/json"
	"fmt"
	"os"
	"testing"
)

func TestInit(t *testing.T) {
	Init("../data/")
	//os.Exit(m.Run())
}

//func TestWritePostIndexMap(t *testing.T) {
//	_, _ = NewPostIndexMapDaoInstance().WritePostIndexMap(2, "abc")
//
//	for _, val := range postIndexMap {
//		for _, item := range val {
//			s, err := json.Marshal(item)
//			if err != nil {
//				os.Exit(-1)
//			}
//
//			fmt.Println(string(s))
//		}
//	}
//	//assert.NotEqual(t, nil, pageInfo)
//	//assert.Equal(t, 5, len(pageInfo.PostList))
//}

func TestWritePostDB(t *testing.T) {
	post, _ := NewPostIndexMapDaoInstance().WritePostIndexMap(2, "abc")
	for _, val := range postIndexMap {
		for _, item := range val {
			s, err := json.Marshal(item)
			if err != nil {
				os.Exit(-1)
			}

			fmt.Println(string(s))
		}
	}

	_ = NewPostDBDaoInstance().WritePostDB(post)
}
