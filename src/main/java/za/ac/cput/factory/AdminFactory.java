package za.ac.cput.factory;
import za.ac.cput.domain.Admin;
import za.ac.cput.util.Helper;


public class AdminFactory {
    public static Admin createAdmin (String id, String role) {
      if (Helper.isNullOrEmpty(id)
          || Helper.isNullOrEmpty(role)) {
          return null;
      }
      return new Admin.Builder()
              .setId(id)
              .setRole(role)
              .build();
    }
}
