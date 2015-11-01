package com.expert.contact.web;


import com.expert.contact.dao.DaoException;
import com.expert.contact.domain.Person;
import com.expert.contact.service.PersonService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class PersonController extends HttpServlet
{
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String url = request.getServletPath();
        HttpSession session = request.getSession();
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        if(url.equals("/main"))
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/main.jsp");
            dispatcher.forward(request, response);
        }

        if(url.equals("/import"))
        {
            try
            {
                PersonService personService = (PersonService) session.getAttribute("personService");
                String uploadInProgress = (String)getServletContext().getAttribute("uploadInProgress");
                boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
                if (isMultipartContent)
                {
                    if(uploadInProgress == null)
                    {
//                    out.println("You are trying to upload<br/>");
                        boolean uploadSuccess = true;
                        String csvSplit = request.getParameter("csv-split");
                        FileItemFactory factory = new DiskFileItemFactory();
                        ServletFileUpload upload = new ServletFileUpload(factory);
                        getServletContext().setAttribute("uploadInProgress", "uploadInProgress");
                        List<FileItem> fileItemList = upload.parseRequest(request);
                        if(fileItemList != null)
                        {
                            for(FileItem f : fileItemList)
                            {
                                if(f.isFormField() && f.getFieldName().equals("csv-split"))
                                {
                                    csvSplit = f.getString();
                                }
                                else if(!f.isFormField() && csvSplit != null)
                                {
                                    uploadSuccess = personService.importPerson(f.getInputStream(), csvSplit);
                                }
                            }
                        }
                        getServletContext().setAttribute("uploadInProgress", null);
                        if(!uploadSuccess)
                        {
                            out.println("Ошибка. Проверьте файл. Возможно выбран неверный разделитель.");
                            return;
                        }
                    }
                    else
                    {
                        out.println("Идет загрузка файла. Попробуйте позже.");
                        return;
                    }
                }
                /*else
                {
                    out.println("You are not trying to upload<br/>");
                }*/
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/import.jsp");
                dispatcher.forward(request, response);
            }
            catch(Exception e)
            {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

        }

        if(url.equals("/contactList"))
        {
            try
            {
                PersonService personService = (PersonService) session.getAttribute("personService");
                int page = 1;
                int recordsPerPage = 4;
                if(request.getParameter("page") != null)
                {
                    page = Integer.parseInt(request.getParameter("page"));
                }
                List<Person> personList = null;
                String sortValue = request.getParameter("sortValue");
                if(sortValue != null && !sortValue.isEmpty())
                {
                    session.setAttribute("sortValue", sortValue);
                }
                if(session.getAttribute("sortValue") != null)
                {
                    sortValue = (String)session.getAttribute("sortValue");
                    personList = personService.getPersonAllPagingSort(sortValue, (page-1)*recordsPerPage, recordsPerPage);
                }
                else
                {
                    personList = personService.getPersonAllPaging((page-1)*recordsPerPage, recordsPerPage);
                }
                int recordsQuantity = personService.getRecordsQuantity();
                int pagesQuantity = (int) Math.ceil(recordsQuantity*1.0/recordsPerPage);
                session.setAttribute("personList", personList);
                session.setAttribute("pagesQuantity", pagesQuantity);
                session.setAttribute("currentPage", page);
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/contactList.jsp");
                dispatcher.forward(request, response);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
    }

}
