
https://www.youtube.com/watch?v=8S8o46avgAw

https://www.youtube.com/watch?v=Ijj5RLSAcYw

2nd round with curl post request sending as a request body.

C:\Temp\delete\del2\a>more data.txt

{
"title": "xyz",
"value": "536"
}

C:\Temp\delete\del2\a>curl -H "Accept: application/json"  -H "Content-Type: application/json"  -X POST -d "@data.txt" "http://localhost:8080/mytest-spring/hello
/postjson"


result:
{"title":"hobby","value":"chess"}

------
3rd round --
requtest to .../postjsonByservice and result


C:\Temp\delete\del2\a>curl -H "Accept: application/json"  -H "Content-Type: application/json"  -X POST -d "@data.txt" "http://localhost:8080/mytest-spring/hello/postjsonByservice"
[{"title":"title","value":"mno"},{"title":"value","value":"777"}]
C:\Temp\delete\del2\a>