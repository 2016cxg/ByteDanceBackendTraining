package service

import (
	"Community/repository"
	"fmt"
	"testing"
)

func TestWriteInfoInit(t *testing.T) {
	repository.Init("../data/")
	//os.Exit(-1)
}
func TestWriteInfo(t *testing.T) {
	//repository.Init("../data/")
	err := NewWritePageInfoFlow(2, "abcde").Do()
	fmt.Println(err)
}
func TestWriteInfo1(t *testing.T) {
	//repository.Init("../data/")
	err := NewWritePageInfoFlow(-1, "abcde").Do()
	fmt.Println(err)
}
func TestWriteInfo2(t *testing.T) {
	//repository.Init("../data/")
	err := NewWritePageInfoFlow(3, "abcde").Do()
	fmt.Println(err)
}
