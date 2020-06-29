<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>诗歌</title>
</head>
<body>
<ul>
    <!--  暂时繁体，简体诗词意思容易有偏差-->
    <#list poems as poem>
        <li><a href="/poems/${poem.uuid}"> ${poem.title}</a></li>
        <li>${poem.author}</li>
        <li>${poem.text}</li>
        <hr/>
    </#list>
</ul>
</body>
</html>
