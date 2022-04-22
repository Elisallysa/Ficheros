package utils;

import dao.UserDAO;
import models.User;

public class ActivationCodeHelper {

	static UserDAO userDAO = new UserDAO();

	public static String generateActivationCode() {

		try {
			int num = 0;
			for (int i = 0; i < 6; i++) {
				while (num == 0) {
					num = (int) (Math.random() * 1000000);
				}
			}
			return String.valueOf(num);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void setActivationCode(User user, String code) {
		userDAO.setActivationCode(user, code);
	}

}
