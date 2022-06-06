xargs -I % -P 5 curl -d "content=abcdefg" "http://127.0.0.1:8080/community/page/post/2" < <(printf '%s\n' {1..50})  & 
xargs -I % -P 5 curl  "http://127.0.0.1:8080/community/page/get/1" < <(printf '%s\n' {1..50}) &
wait
