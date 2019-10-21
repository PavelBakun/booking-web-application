package by.epam.bakun.booking.web.command;

import by.epam.bakun.booking.dao.HotelDAO;
import by.epam.bakun.booking.model.Hotel;
import by.epam.bakun.booking.web.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ViewHotelCommand implements ActionCommand {
    private static final String PARAM_NAME_VIEW_HOTEL = "viewHotel";
    private static final String PARAM_NAME_VIEW_HOTELID = "hotelId";
    @Override
    public String execute(HttpServletRequest request) {
        HotelDAO hotelDAO = new HotelDAO();
        String viewHotel = request.getParameter(PARAM_NAME_VIEW_HOTELID);
        if (viewHotel == null) {
            List<Hotel> list = hotelDAO.getAll();
            request.setAttribute("listStorage", list);
            return ConfigurationManager.getProperty("path.page.hotels");
        } else {
            Hotel hotel = hotelDAO.getById(Integer.parseInt(viewHotel));
            request.setAttribute("hotel", hotel);
            return ConfigurationManager.getProperty("path.page.viewHotel");
        }
    }
}
