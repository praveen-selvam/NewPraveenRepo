package com.autotrek.instantauto.test;

import com.autotrek.instantauto.web.dto.RoleDto;

/**
 *
 * @author praveens
 */
public class RoleHelperUtil {

    public static RoleDto createMockRoleForCreate() {
        RoleDto returnRole = new RoleDto();
        returnRole.roleName = StringHelperUtil.generateRandomString(10);
        returnRole.customer = "TestCoustomer";
        returnRole.description = "TestDescription";

        return returnRole;
    }

    public static RoleDto editMockRoleForEdit() {
        RoleDto returnRole = new RoleDto();
        returnRole.roleName = StringHelperUtil.generateRandomString(10);
        returnRole.customer = "updated customer";
        returnRole.description = "updated  TestDescription";

        return returnRole;
    }
    public static RoleDto deleteMockRoleForDelete() {
      RoleDto returnRole = new RoleDto();
        returnRole.roleName = StringHelperUtil.generateRandomString(10);
        returnRole.customer = "updated customer";
        returnRole.description = "updated  TestDescription";

        return returnRole;
      
    }
}
