# 接口文章

server = http://127.0.0.1

| 模块    | 端口  |
| ------- | ----- |
| eureka  | 20001 |
| user    | 20002 |
| article | 20003 |
| image   | 20004 |

## 1.用户注册

http://127.0.0.1:20002/json/user

### POST /json/user

用户注册。

### 参数

类型：x-www-form-urlencoded

| 参数      | 类型   | 是否为空 | 备注                     | 合法值              |
| :-------- | :----- | :------- | :----------------------- | :------------------ |
| user_name | string | 否       | 用户名(昵称)             | 30个字符以内        |
| phone     | string | 否       | 电话号码                 | 11位数字            |
| password  | string | 否       | 密码                     | 20个字符以内        |
| face_url  | string | 否       | 微信客户端生成的用户头像 | xxx.png             |
| gender    | string | 否       | 性别                     | 0女生1男生 一个字符 |
| city      | string | 否       | 城市                     | 10个字符以内        |
| province  | string | 否       | 省份                     | 10个字符以内        |

### 响应

类型：json

成功实例：

```json
{
    "code": 0,
    "msg": "成功",
    "data": null
}
```

失败实例：

```json
{
    "code": 3,
    "msg": "手机号已注册过",
    "data": null
}
```

## 2.用户登录

### GET /json/user

用户登录。登录后返回用户完整信息，客户端应该缓存一份。

### 参数

类型：x-www-form-urlencoded

| 参数     | 类型   | 是否为空 | 备注     | 合法值       |
| -------- | :----- | :------- | :------- | :----------- |
| phone    | string | 否       | 电话号码 | 11位数字     |
| password | string | 否       | 密码     | 20个字符以内 |

### 响应

类型：json

成功实例：

```json
{
    "code": 0,
    "msg": "成功",
    "data": {
        "userId": 4,
        "userName": "悬崖之上",
        "phone": "12345678913",
        "password": "T4mLlqC/Z6Ju27YUIWkMxg==",
        "faceUrl": "123.png",
        "province": "四川",
        "city": "成都",
        "gender": "0"
    }
}
```

失败实例：

```json
{
    "code": 6,
    "msg": "密码错误",
    "data": null
}
{
    "code": 5,
    "msg": "用户不存在",
    "data": null
}
```

## 3.发布文章

### POST /json/article

用户发布文章。

## 参数

类型：x-www-form-urlencoded

| 参数         | 类型   | 是否为空 | 备注                                 | 合法值        |
| ------------ | ------ | -------- | ------------------------------------ | ------------- |
| user_id      | string | 否       | 唯一标识一个用户                     |               |
| encrypt_code | string | 否       | 加密后的user_id 用来验证请求的合法性 |               |
| title        | string | 否       | 文章标题                             | 100个字符以内 |
| content      | string | 否       | 文章内容                             | 500个字符以内 |
| timestamp    | string | 否       | 文章发布的时间戳                     | 10个字符      |
| image_num    | string | 否       | 文章附带图片总数                     |               |

### 响应

类型：json

成功实例：

```json
{
    "code": 0,
    "msg": "成功",
    "data": null
}
```

失败实例：

```json
{
    "code": 7,
    "msg": "非法访问",
    "data": null
}
```

## 4.获取我发布的文章

### GET /json/my_articles

获取我发布过的所有文章。按时间降序排列。

### 参数

类型：x-www-form-urlencoded

| 参数         | 类型   | 是否为空 | 备注                            | 合法值 |
| ------------ | ------ | -------- | ------------------------------- | ------ |
| user_id      | string | 否       | 唯一标识一个用户                |        |
| encrypt_code | string | 否       | 加密后的id 用来验证请求的合法性 |        |

### 响应

类型：json

成功实例：

```json
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "articleId": 4,
            "userId": 4,
            "title": "行走于钢筋密林，幽定格重庆的过去与未来时隔十载，我回到重庆要寻找的是什么?",
            "content": "可能是青春的回忆，毕业十周年返校聚会;\r\n可能是陪爱人打卡《少年的你》拍摄地;\r\n还可能是为了亲临目睹重庆的种种变化。\r\n当我重新踏入这座留存于记忆中的第二故乡时，涌上心头的是一种既陌生、又熟悉的复杂感。\r\n抬头仰望发现空中是穿梭于钢筋密林间的索道、轻轨、立交桥。\r\n我便在返校聚会之余拿起相机，把重庆的过去与未来定格在方寸之间。\r\n",
            "totalLike": 913,1
            "timeStamp": 1621562539,
            "imageNum": 3,
            "isLike": 0,
            "userName": null
        },
        {
            "articleId": 5,
            "userId": 4,
            "title": "上海|这样拍外滩，让你的照片制霸票圈",
            "content": "上海人的生活总是离不开马路\r\n每一条马路都藏着这个城市的活力密码\r\n来上海外滩当然是必去的!\r\n外滩的建筑、外滩的马路、外滩的一草一木，一路走来，都代表了上海的历史和故事!啥?来外滩只知道拍四件套?白来了晓得伐!来跟着lucky拍一拍外滩附近的马路刷爆你的票圈吧！\r\n",
            "totalLike": 1225,
            "timeStamp": 1622600529,
            "imageNum": 3,
            "isLike": 1,
            "userName": null
        },
        {
            "articleId": 6,
            "userId": 4,
            "title": "鬼才建筑师Heatherwick|纽约漂浮公园",
            "content": "Thomas Heatherwick\r\n人称设计鬼才设计界的颠覆者~\r\n哈德逊河55号码头\r\n这座耗资1.7亿美元的码头\r\n位于纽约哈德逊河公园码头54和码头56之间是一个全新模式的公共码头、公共公园\r\n花瓣状的外观如同波浪\r\n漂浮在河水之上、十分唯美\r\n周围被郁郁葱葱绿色植被覆盖\r\n为城市增添了一道靓丽的景观\r\n",
            "totalLike": 669,
            "timeStamp": 1622728040,
            "imageNum": 3,
            "isLike": 1,
            "userName": null
        },
        {
            "articleId": 7,
            "userId": 4,
            "title": "just for test",
            "content": "this is a test",
            "totalLike": 1231,
            "timeStamp": 1622979933,
            "imageNum": 4,
            "isLike": 0,
            "userName": null
        },
        {
            "articleId": 8,
            "userId": 4,
            "title": "just for test2",
            "content": "this is a test2",
            "totalLike": 0,
            "timeStamp": 1622979955,
            "imageNum": 4,
            "isLike": 0,
            "userName": null
        }
    ]
}
imageList[0]:http://footprint.wanfengcxz.cn/images/6/2/0.png
imageList[1]:http://footprint.wanfengcxz.cn/images/6/2/0.png
face_url:http://footprint.wanfengcxz.cn/images/6/0.png 
```

失败实例：

```json
{
    "code": 7,
    "msg": "非法访问",
    "data": null
}
```

## 5.随机获取文章

### GET /json/random_articles

随机获取一些文章。

### 参数

类型：x-www-form-urlencoded

| 参数         | 类型   | 是否为空 | 备注                            | 合法值 |
| ------------ | ------ | -------- | ------------------------------- | ------ |
| user_id      | string | 否       | 唯一标识一个用户                |        |
| encrypt_code | string | 否       | 加密后的id 用来验证请求的合法性 |        |

### 响应

类型：json

成功实例：

```json
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "articleId": 3,
            "userId": 8,
            "title": "杭州新地标萧山杭州之门像不像倒着的秋裤",
            "content": "位于杭州奥体博览城东至杭州国际博览中心\r\n西至综合训练馆\r\n据说有310米未来有望成为钱江南岸的新地标\r\n",
            "totalLike": 315,
            "timeStamp": 1622520739,
            "imageNum": 3,
            "isLike": 1,
            "userName": "寂静之地"
        },
        {
            "articleId": 2,
            "userId": 6,
            "title": "湖里万达也开KKV了!",
            "content": "今天偶然发现的，之前去过加州那家\r\n加州的有-面饰品和红酒墙万达有泡面墙间\r\n没想到大家这么喜欢，又去一-次给大家更新一下万达这家有一小块地酒品，加州是一面红酒墙\r\n",
            "totalLike": 126,
            "timeStamp": 1622616439,
            "imageNum": 3,
            "isLike": 1,
            "userName": "哆啦A梦"
        },
        {
            "articleId": 4,
            "userId": 4,
            "title": "行走于钢筋密林，幽定格重庆的过去与未来时隔十载，我回到重庆要寻找的是什么?",
            "content": "可能是青春的回忆，毕业十周年返校聚会;\r\n可能是陪爱人打卡《少年的你》拍摄地;\r\n还可能是为了亲临目睹重庆的种种变化。\r\n当我重新踏入这座留存于记忆中的第二故乡时，涌上心头的是一种既陌生、又熟悉的复杂感。\r\n抬头仰望发现空中是穿梭于钢筋密林间的索道、轻轨、立交桥。\r\n我便在返校聚会之余拿起相机，把重庆的过去与未来定格在方寸之间。\r\n",
            "totalLike": 913,
            "timeStamp": 1621562539,
            "imageNum": 3,
            "isLike": 0,
            "userName": "悬崖之上"
        }
    ]
}
imageList[0]:http://footprint.wanfengcxz.cn/images/6/2/0.png
imageList[1]:http://footprint.wanfengcxz.cn/images/6/2/0.png
face_url:http://footprint.wanfengcxz.cn/images/6/0.png 
```

失败实例：

```json
{
    "code": 7,
    "msg": "非法访问",
    "data": null
}
```

## 6.上传图片

### POST /image/image

用户上传图片。结合前端微信小程序，略。

图片上传说明：

**图片存储路径**

```json
imagePath="D:\\Coding\\tmp\\" + userId + "\\" + articleId + "\\"
```

**说明**

以用户ID和文章ID来唯一标识一张图片，对于一篇文章的所有图片，以1.png 2.png 3.png .....依次进行命名存储。当图片为用户的头像时，将articleId置为0，然后以0.png进行命名存储。

**eg**

userId=4  articleId=5 该文章有两张图片

则该文章第一张图片的URL为

```json
"D:\\Coding\\tmp\\4\\5\\1.png"
```

第二章图片的URL为

```json
"D:\\Coding\\tmp\\4\\5\\2.png"
```

该用户头像URL为

```json
"D:\\Coding\\tmp\\4\\0\\0.png"
```

### 7.喜欢

### POST /json/like

用户给文章点赞

### 参数

类型：x-www-form-urlencoded

| 参数         | 类型   | 是否为空 | 备注                            | 合法值 |
| ------------ | ------ | -------- | ------------------------------- | ------ |
| user_id      | string | 否       | 唯一标识一个用户                |        |
| encrypt_code | string | 否       | 加密后的id 用来验证请求的合法性 |        |
| article_id   | string | 否       | 用户当前点赞文章的id            |        |

### 响应

类型：json

成功实例：

```json
{
    "code": 0,
    "msg": "成功",
    "data": null
}
```

失败实例：

```json
{
    "code": 4,
    "msg": "文章不存在",
    "data": null
}
{
    "code": 7,
    "msg": "非法访问",
    "data": null
}
```

### 7.不喜欢

### POST /json/unlike

用户取消点赞

### 参数

类型：x-www-form-urlencoded

| 参数         | 类型   | 是否为空 | 备注                            | 合法值 |
| ------------ | ------ | -------- | ------------------------------- | ------ |
| user_id      | string | 否       | 唯一标识一个用户                |        |
| encrypt_code | string | 否       | 加密后的id 用来验证请求的合法性 |        |
| article_id   | string | 否       | 用户当前取消点赞文章的id        |        |

### 响应

类型：json

成功实例：

```json
{
    "code": 0,
    "msg": "成功",
    "data": null
}
```

失败实例：

```json
{
    "code": 4,
    "msg": "文章不存在",
    "data": null
}
{
    "code": 7,
    "msg": "非法访问",
    "data": null
}
```