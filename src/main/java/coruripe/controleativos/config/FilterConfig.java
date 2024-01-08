package coruripe.controleativos.config;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class FilterConfig implements Filter {

	//private String[] enderecosValidos = { "/automacaocfl/", "/automacaocfl/login", "/automacaocfl/script/imports.js", "/automacaocfl/css/login.css", "/automacaocfl/favicon.png", "/automacaocfl/logar", "/automacaocfl/api/proposta/consultar" };
	//private String[] enderecosValidos = { "/", "/login", "/script/imports.js", "/css/login.css", "/favicon.png", "/logar"};

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		//HttpServletResponse resp = (HttpServletResponse) response;
		
		System.out.println(req.getMethod() + " - " + req.getRequestURI().toString());
		chain.doFilter(request, response);
		
		/*
		if (req.getSession().getAttribute("usuario") == null && !Arrays.asList(this.enderecosValidos).contains(req.getRequestURI().toString())) {
			System.out.println("Sessão Inválida - [" + req.getRequestURI().toString() + "]");
			resp.sendRedirect("/automacaocfl/login");

		} else if (req.getSession().getAttribute("usuario") == null && Arrays.asList(this.enderecosValidos).contains(req.getRequestURI().toString())) {
			System.out.println("Sessão Inválida - [" + req.getRequestURI().toString() + "]");
			chain.doFilter(request, response);

		} else if (req.getSession().getAttribute("usuario") != null) {
			Usuario user = (Usuario) req.getSession().getAttribute("usuario");
			System.out.println("Sessão Válida -  [" + req.getRequestURI().toString() + "]" + " - User: " + user.getLoginUsuario());
			chain.doFilter(request, response);
		}*/
	}

}
