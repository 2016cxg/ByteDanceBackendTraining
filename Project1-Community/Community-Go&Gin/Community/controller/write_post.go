package controller

import (
	"strconv"

	"Community/service"
)

func WritePost(topicIdStr string, content string) *PageData {
	topicId, err := strconv.ParseInt(topicIdStr, 10, 64)
	if err != nil {
		return &PageData{
			Code: -1,
			Msg:  err.Error(),
		}
	}
	err = service.NewWritePageInfoFlow(topicId, content).Do()
	if err != nil {
		return &PageData{
			Code: -1,
			Msg:  err.Error(),
		}
	}
	return &PageData{
		Code: 0,
		Msg:  "success",
	}

}
