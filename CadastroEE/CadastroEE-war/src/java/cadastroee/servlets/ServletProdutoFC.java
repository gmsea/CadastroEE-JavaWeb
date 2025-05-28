package cadastroee.servlets;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import cadastroee.model.Produto;
import cadastroee.controller.ProdutoFacadeLocal;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletProdutoFC", urlPatterns = {"/ServletProdutoFC"})
public class ServletProdutoFC extends HttpServlet {

    @EJB
    private ProdutoFacadeLocal facade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        String destino;

        try {
            if (acao == null || acao.equals("listar")) {
                System.out.println("Listando produtos...");
                List<Produto> produtos = facade.findAll();
                request.setAttribute("produtos", produtos);
                destino = "ProdutoLista.jsp";

            } else if (acao.equals("formIncluir")) {
                destino = "ProdutoDados.jsp";

            } else if (acao.equals("formAlterar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Produto p = facade.find(id);
                request.setAttribute("produto", p);
                destino = "ProdutoDados.jsp";

            } else if (acao.equals("incluir")) {
                Produto p = new Produto();
                p.setNome(request.getParameter("nome"));
                p.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));

                String precoStr = request.getParameter("precoVenda").replace(",", ".");
                try {
                    p.setPrecoVenda((float) Double.parseDouble(precoStr));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    p.setPrecoVenda(0.0f);
                }

                facade.create(p);
                request.setAttribute("produtos", facade.findAll());
                destino = "ProdutoLista.jsp";

            } else if (acao.equals("alterar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Produto p = facade.find(id);
                p.setNome(request.getParameter("nome"));
                p.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));

                String precoStr = request.getParameter("precoVenda").replace(",", ".");
                try {
                    p.setPrecoVenda((float) Double.parseDouble(precoStr));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    p.setPrecoVenda(0.0f);
                }

                facade.edit(p);
                request.setAttribute("produtos", facade.findAll());
                destino = "ProdutoLista.jsp";

            } else if (acao.equals("excluir")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Produto p = facade.find(id);
                facade.remove(p);
                request.setAttribute("produtos", facade.findAll());
                destino = "ProdutoLista.jsp";

            } else {
                destino = "ProdutoLista.jsp";
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(destino);
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro no processamento do servlet.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}