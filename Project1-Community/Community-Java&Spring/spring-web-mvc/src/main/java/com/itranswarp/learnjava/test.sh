xargs -I % -P 5 curl -d content=aaaa http://127.0.0.1:8080//Community/PageInfo/post/2 < <(printf '%s\n' {1..10})  & 
xargs -I % -P 5 curl  http://127.0.0.1:8080//Community/PageInfo/get/2 < <(printf '%s\n' {1..10}) &
wait
