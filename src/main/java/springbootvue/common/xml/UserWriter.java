package springbootvue.common.xml;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import springbootvue.common.User;

public class UserWriter {
	public static UserWriter instance() {
		return new UserWriter();
	}

	public String writeRquest() {
		String xml = "";
		// writes the users
		XMLOutputFactory xof = XMLOutputFactory.newInstance();
		XMLStreamWriter xsw = null;
		try {

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			xsw = xof.createXMLStreamWriter(baos);

			xsw.writeStartDocument();
			xsw.writeStartElement("users");
			
			ArrayList<User> users = new ArrayList<>();
			LocalDate date1 = LocalDate.of(2000, Month.MARCH, 21);
			LocalDate date2 = LocalDate.of(2012, Month.OCTOBER, 30);
			LocalDate date3 = LocalDate.of(2011, Month.JANUARY, 1);
			users.add(new User("John Smith", 22, date1));
			users.add(new User("James Brown", 31, date2));
			users.add(new User("Tom Hanks", 16, date3));

			for (User u : users) {
				xsw.writeStartElement("user");
				xsw.writeAttribute("age", Integer.toString(u.getAge()));
				xsw.writeStartElement("name");
				xsw.writeCharacters(u.getFirstName());
				xsw.writeEndElement();
				xsw.writeStartElement("registered");
				xsw.writeCharacters(User.dateTimeFormatter.format(u.getRegistered()));
				xsw.writeEndElement();
				xsw.writeEndElement();
			}

			xsw.writeEndDocument();
			xsw.flush();
			xml = baos.toString("UTF-8");
		} catch (Exception e) {
			System.err.println("Unable to write the file: " + e.getMessage());
		} finally {
			try {
				if (xsw != null) {
					xsw.close();
				}
			} catch (Exception e) {
				System.err.println("Unable to close the file: " + e.getMessage());
			}
		}
		return xml;

	}

}
