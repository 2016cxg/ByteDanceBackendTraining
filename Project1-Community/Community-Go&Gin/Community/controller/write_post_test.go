package controller

import (
	"fmt"
	"os"
	"testing"
)

import (
	"Community/repository"
)

func TestMain(m *testing.M) {
	repository.Init("../data/")
	os.Exit(m.Run())
}
func TestWritePost(t *testing.T) {
	pageData := WritePost("1", "abcd")
	fmt.Println(pageData)
}

func TestWritePost1(t *testing.T) {
	pageData := WritePost("-1", "abcd")
	fmt.Println(pageData)
}
func TestWritePost2(t *testing.T) {
	pageData := WritePost("3", "abcd")
	fmt.Println(pageData)
}
