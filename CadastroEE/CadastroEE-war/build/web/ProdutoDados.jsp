<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="cadastroee.model.Produto" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    Produto p = (Produto) request.getAttribute("produto");
    String acao = (p == null) ? "incluir" : "alterar";
%>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= (p == null) ? "Incluir Produto" : "Alterar Produto" %></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
</head>
<body class="container mt-4">
    <h2><%= (p == null) ? "Incluir Produto" : "Alterar Produto" %></h2>

    <form action="ServletProdutoFC" method="post" class="form">
        <input type="hidden" name="acao" value="<%= acao %>"/>
        <% if (p != null) { %>
            <input type="hidden" name="id" value="<%= p.getIdProduto() %>"/>
        <% } %>

        <div class="mb-3">
            <label for="nome" class="form-label">Nome</label>
            <input type="text" name="nome" id="nome" class="form-control" value="<%= (p != null) ? p.getNome() : "" %>"/>
        </div>

        <div class="mb-3">
            <label for="quantidade" class="form-label">Quantidade</label>
            <input type="number" name="quantidade" id="quantidade" class="form-control" value="<%= (p != null) ? p.getQuantidade() : "" %>"/>
        </div>

        <div class="mb-3">
            <label for="preco" class="form-label">Preço</label>
            <input type="text" name="precoVenda" id="preco" class="form-control" value="<%= (p != null) ? p.getPrecoVenda() : "" %>"/>
        </div>

        <button type="submit" class="btn btn-primary">
            <%= (p == null) ? "Incluir" : "Alterar" %>
        </button>
    </form>
</body>
</html>
