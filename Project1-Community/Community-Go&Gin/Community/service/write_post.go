package service

//业务层write_post
//转换controller的write_post传递的参数parent_id为int
//并检查parent_id是否满足已经有的parentId范围，如果不满足就报错
//如果满足就向下传递参数parent_id和content

import (
	"Community/repository"
	"errors"
)

type WritePageInfoFlow struct {
	topicId int64
	content string
}

func NewWritePageInfoFlow(topId int64, content_ string) *WritePageInfoFlow {
	return &WritePageInfoFlow{
		topicId: topId,
		content: content_,
	}
}

func (f *WritePageInfoFlow) Do() error {
	if err := f.checkParam(); err != nil {
		return err
	}
	if err := f.writeInfo(); err != nil {
		return err
	}

	return nil
}

func (f *WritePageInfoFlow) checkParam() error {
	if f.topicId <= 0 {
		return errors.New("topic id must be larger than 0")
	}

	maxTopicId, err := repository.NewPostIndexMapDaoInstance().MaxTopicIndex()
	if err != nil {
		return err
	}
	if f.topicId > maxTopicId {
		return errors.New("topic id exceeding current max topic id")
	}

	return nil
}

func (f *WritePageInfoFlow) writeInfoAtomic() error {
	post, err := repository.NewPostIndexMapDaoInstance().WritePostIndexMap(f.topicId, f.content)
	if err != nil {
		return err
	}
	err = repository.NewPostDBDaoInstance().WritePostDB(post)
	if err != nil {
		return err
	}

	return nil
}

func (f *WritePageInfoFlow) writeInfo() error {

	rw.Lock()
	err := f.writeInfoAtomic()
	rw.Unlock()

	if err != nil {
		return err
	}
	return nil
}
