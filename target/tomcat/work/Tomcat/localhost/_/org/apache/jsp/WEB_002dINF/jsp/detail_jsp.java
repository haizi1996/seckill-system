/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2017-07-20 01:38:09 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class detail_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/WEB-INF/jsp/common/head.jsp", Long.valueOf(1500293349000L));
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"zh-CN\">\n");
      out.write("<head>\n");
  
    String path = request.getContextPath();  
    String basePath = request.getScheme() + "://"  
            + request.getServerName() + ":" + request.getServerPort()  
            + path + "/";  
    pageContext.setAttribute("basePath",basePath);    

      out.write("\n");
      out.write("\n");
      out.write("<meta charset=\"utf-8\">\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("<link rel=\"stylesheet\"\n");
      out.write("\thref=\"//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css\">\n");
      out.write("\t\n");
      out.write("<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->\n");
      out.write("<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->\n");
      out.write("<!--[if lt IE 9]>\n");
      out.write("      <script src=\"//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js\"></script>\n");
      out.write("      <script src=\"//cdn.bootcss.com/respond.js/1.4.2/respond.min.js\"></script>\n");
      out.write("    <![endif]-->\n");
      out.write("\n");
      out.write("<title>秒杀详情页</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("\t<div class=\"container\">\n");
      out.write("\t\t<div class=\"panel panel-default text-center\">\n");
      out.write("\t\t\t<div class=\"panel-heading\">\n");
      out.write("\t\t\t\t<h1>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${seckill.name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</h1>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div class=\"panel-body\">\n");
      out.write("\t\t\t\t<h2 class=\"text-danger\">\n");
      out.write("\t\t\t\t\t<!-- 显示time图标 -->\n");
      out.write("\t\t\t\t\t<span class=\"glyphicon glyphicon-time\"></span>\n");
      out.write("\t\t\t\t\t<!-- 展示倒计时 -->\n");
      out.write("\t\t\t\t\t<span class=\"glyphicon\" id=\"seckillBox\"></span>\n");
      out.write("\t\t\t\t</h2>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("\n");
      out.write("\t<!-- 登录弹出层，输入电话 -->\n");
      out.write("\t<div id=\"killPhoneModal\" class=\"modal fade\">\n");
      out.write("\t\t<div class=\"modal-dialog\">\n");
      out.write("\t\t\t<div class=\"modal-content\">\n");
      out.write("\t\t\t\t<div class=\"modal-header\">\n");
      out.write("\t\t\t\t\t<h3 class=\"modal-title text-center\">\n");
      out.write("\t\t\t\t\t\t<span class=\"glyphicon glyphicon-phone\"></span>秒杀电话：\n");
      out.write("\t\t\t\t\t</h3>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<div class=\"modal-body\">\n");
      out.write("\t\t\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t\t\t<div class=\"col-xs-8 col-xs-offset-2\">\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" name=\"killphone\" id=\"killphoneKey\"\n");
      out.write("\t\t\t\t\t\t\t\tplaceholder=\"填手机号^O^\" class=\"form-control\" />\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<div class=\"modal-footer\">\n");
      out.write("\t\t\t\t\t<span id=\"killphoneMessage\" class=\"glyphicon\"></span>\n");
      out.write("\t\t\t\t\t<button type=\"button\" id=\"killPhoneBtn\" class=\"btn btn-success\">\n");
      out.write("\t\t\t\t\t\t<span class=\"glyphicon glyphicon-phone\"></span> Submit\n");
      out.write("\t\t\t\t\t</button>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("\n");
      out.write("\t<script src=\"//cdn.bootcss.com/jquery/1.11.3/jquery.min.js\"></script>\n");
      out.write("\t<script src=\"//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js\"></script>\n");
      out.write("\t<script src=\"//cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js\"></script>\n");
      out.write("\t<script src=\"//cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js\"></script>\n");
      out.write("\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${basePath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("resources/js/seckill.js\"  type=\"text/javascript\"></script>\n");
      out.write("\t<script type=\"text/javascript\">\n");
      out.write("\t$(function(){\n");
      out.write("\t\t//使用EL表达式传入参数\n");
      out.write("\t\tseckill.detail.init({\n");
      out.write("\t\t\tseckillId : ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${seckill.seckillId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(",\n");
      out.write("\t\t\tstartTime : ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${seckill.startTime.time}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(",//毫秒\n");
      out.write("\t\t\tendTime : ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${seckill.endTime.time}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\n");
      out.write("\t\t});\n");
      out.write("\t});\n");
      out.write("\t</script>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
