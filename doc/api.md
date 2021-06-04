# 接口说明

## 1.用户注册

### POST /json/user

用户注册。

### 参数

类型：x-www-form-urlencoded

| 参数      | 类型   | 是否为空 | 备注                     | 合法值       |
| :-------- | :----- | :------- | :----------------------- | :----------- |
| user_name | string | 否       | 用户名(昵称)             | 30个字符以内 |
| phone     | string | 否       | 电话号码                 | 11位数字     |
| password  | string | 否       | 密码                     | 20个字符以内 |
| face_url  | string | 否       | 微信客户端生成的用户头像 | xxx.png      |

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
        "user_id": 6,
        "user_name": "EASY2",
        "phone": "15137412355",
        "password": "stRdD02ctYpoFIdAFHVKAQ==",
        // password被替换成了一个加密字段，用于以后访问时验证用户的合法性
        "face_url": "xx.png"
        // 服务器会将微信客户端生成的头像在本地保存一份，然后返回其URL
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
| time_stamp   | string | 否       | 文章发布的时间戳                     |               |

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

### GET /json/my_article

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
            "article_id": 2,
            "user_id": 6,
            "title": "EASY2",
            "content": "我终于云部署成功啦",
            "total_like": 3,
            "image_num":2,
            "time_stamp": 1610199582
        },
        {
            "article_id": 3,
            "user_id": 6,
            "title": "EASY2",
            "content": "真的花了我好大力气",
            "total_likes": 0,
            "image_num":3,
            "time_stamp": 1610199511
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

### GET /json/articles

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
            "article_id": 2,
            "user_id": 6,
            "title": "EASY2",
            "content": "我终于云部署成功啦",
            "total_like": 3,
            "image_num":2,
            "time_stamp": 1610199582
        },
        {
            "article_id": 3,
            "user_id": 6,
            "title": "EASY2",
            "content": "真的花了我好大力气",
            "total_likes": 0,
            "image_num":3,
            "timestamp": 1610199511
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

### POST /json/image

用户上传图片。

### 参数

类型：x-www-form-urlencoded

| 参数         | 类型   | 是否为空 | 备注                            | 合法值 |
| ------------ | ------ | -------- | ------------------------------- | ------ |
| user_id      | string | 否       | 唯一标识一个用户                |        |
| encrypt_code | string | 否       | 加密后的id 用来验证请求的合法性 |        |
| article_id   | string | 否       | 唯一标识一篇文章                |        |
| filePath     | string | 否       | 本地要上传图片的路径            |        |

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