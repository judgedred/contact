<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Import</title>
</head>
<body>
<h2>Upload csv</h2>

<form id="contact" name="contact" action="import" method="post" enctype="multipart/form-data">
    <p><select name="csv-split">
        <option selected disabled>Выберите csv разделитель</option>
        <option value=",">Запятая (",")</option>
        <option value=";">Точка с запятой (";")</option>
    </select></p>
    <p><input type="file" size="30" name="cotact-import" id="contact-import"/></p>
    <p><input type="submit" value="Загрузить"/></p>
</form>
</body>
</html>
