package com.pirogsoft.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import com.pirogsoft.mafia.MafiaGame;
import com.sun.rmi.rmid.ExecOptionPermission;

/**
 * Created by Andrey on 18.10.2016.
 */
public class MainServlet extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        try {
            //Создаем игру, получаем текущее состояние (востанавливаем из БД)
//            MafiaGame app = new MafiaGame();
//
//            //Если пришел параметр nextState = true - вычисляем следующую стадию
//
//            if (req.getParameter("nextState").equals("true")) {
//                app.setCurrentState(app.getCurrentState().getNextState(req, app));
//            }
//
//            // Рендерим
//            String viewFileName = app.getCurrentState().getViewFileName();
            String viewFileName = "index.jsp";
            req.getRequestDispatcher("/WEB-INF/views/" + viewFileName).forward(req, res);
        }
        catch(Exception e)
        {
            e.printStackTrace(res.getWriter());
        }
    }
}
