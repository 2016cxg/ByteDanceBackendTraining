xargs -I % -P 5 curl -d  name=abc "http://127.0.0.1:8000/Community/PageInfo/post/1" < <(printf '%s\n' {1..10})  & 
xargs -I % -P 5 curl "http://127.0.0.1:8000/Community/PageInfo/get/1" < <(printf '%s\n' {1..10}) &
wait
