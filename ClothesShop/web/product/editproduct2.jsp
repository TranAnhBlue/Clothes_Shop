<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Product</title>
    <!-- Include your CSS and JS files here -->
</head>
<body>
    <h1>Edit Product</h1>
    <form action="editproduct" method="post">
        <input type="hidden" name="productId" value="${product.pid}" />
        
        <label for="name">Product Name:</label>
        <input type="text" id="name" name="name" value="${product.name}" required />
        
        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" value="${product.quantity}" required />
        
        <label for="price">Price:</label>
        <input type="number" step="0.01" id="price" name="price" value="${product.price}" required />
        
        <label for="describe">Description:</label>
        <textarea id="describe" name="describe">${product.describe}</textarea>
        
        <label for="img">Image URL:</label>
        <input type="text" id="img" name="img" value="${product.img}" required />
        
        <label for="releaseDate">Release Date:</label>
        <input type="date" id="releaseDate" name="releaseDate" value="${product.releaseDate}" required />
        
        <label for="category">Category:</label>
        <select id="category" name="category" required>
            <c:forEach var="category" items="${categories}">
                <option value="${category.id}" ${category.id == product.category.cid ? 'selected' : ''}>${category.name}</option>
            </c:forEach>
        </select>
        
        <label for="brand">Brand:</label>
        <select id="brand" name="brand" required>
            <c:forEach var="brand" items="${brands}">
                <option value="${brand.id}" ${brand.id == product.brand.bid ? 'selected' : ''}>${brand.name}</option>
            </c:forEach>
        </select>
        
        <label for="gender">Gender:</label>
        <select id="gender" name="gender" required>
            <c:forEach var="gender" items="${genders}">
                <option value="${gender.id}" ${gender.id == product.gender.gid ? 'selected' : ''}>${gender.name}</option>
            </c:forEach>
        </select>
        
        <label for="sizes">Sizes:</label>
        <c:forEach var="size" items="${sizes}">
            <input type="checkbox" id="size${size.sid}" name="size" value="${size.id}" ${product.sizes.contains(size) ? 'checked' : ''}>
            <label for="size${size.sid}">${size.name}</label>
        </c:forEach>
        
        <button type="submit">Update Product</button>
    </form>
</body>
</html>
