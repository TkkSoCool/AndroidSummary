package com.tkk.androidsummary.knowledgepoint.frame.Retrofit;

/**
 * Created  on 2018-05-17
 *
 * @author 唐开阔
 * @describe
 */
public class UserInfo {

    /**
     * user : {"account":"tkk","age":"","bloodGroup":"","companyId":"9419","companyName":"成都第二绕城高速","createAt":1526544042468,"description":"","email":"","enabled":"YES","gender":"","id":"db93dbbb41e649e394eb51abe36c0f7c","initPassword":"","mobile":"13689028340","name":"tkk","oldId":"","orgBizCode":"001002001001001001001002","orgId":"9122","orgName":"六中队","orgTreeCode":"001002001001001001001002","password":"","phone":"","phoneExt":"","photo":"","roleId":"","roleName":"","status":"ACTIVE","title":"","updateAt":1526544963826}
     * token : b26fdaa6327f4f6f8b312694f1231c2d
     */

    private UserEntity user;
    private String token;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class UserEntity {
        /**
         * account : tkk
         * age :
         * bloodGroup :
         * companyId : 9419
         * companyName : 成都第二绕城高速
         * createAt : 1526544042468
         * description :
         * email :
         * enabled : YES
         * gender :
         * id : db93dbbb41e649e394eb51abe36c0f7c
         * initPassword :
         * mobile : 13689028340
         * name : tkk
         * oldId :
         * orgBizCode : 001002001001001001001002
         * orgId : 9122
         * orgName : 六中队
         * orgTreeCode : 001002001001001001001002
         * password :
         * phone :
         * phoneExt :
         * photo :
         * roleId :
         * roleName :
         * status : ACTIVE
         * title :
         * updateAt : 1526544963826
         */

        private String account;
        private String age;
        private String bloodGroup;
        private String companyId;
        private String companyName;
        private long createAt;
        private String description;
        private String email;
        private String enabled;
        private String gender;
        private String id;
        private String initPassword;
        private String mobile;
        private String name;
        private String oldId;
        private String orgBizCode;
        private String orgId;
        private String orgName;
        private String orgTreeCode;
        private String password;
        private String phone;
        private String phoneExt;
        private String photo;
        private String roleId;
        private String roleName;
        private String status;
        private String title;
        private long updateAt;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getBloodGroup() {
            return bloodGroup;
        }

        public void setBloodGroup(String bloodGroup) {
            this.bloodGroup = bloodGroup;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public long getCreateAt() {
            return createAt;
        }

        public void setCreateAt(long createAt) {
            this.createAt = createAt;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEnabled() {
            return enabled;
        }

        public void setEnabled(String enabled) {
            this.enabled = enabled;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInitPassword() {
            return initPassword;
        }

        public void setInitPassword(String initPassword) {
            this.initPassword = initPassword;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOldId() {
            return oldId;
        }

        public void setOldId(String oldId) {
            this.oldId = oldId;
        }

        public String getOrgBizCode() {
            return orgBizCode;
        }

        public void setOrgBizCode(String orgBizCode) {
            this.orgBizCode = orgBizCode;
        }

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getOrgTreeCode() {
            return orgTreeCode;
        }

        public void setOrgTreeCode(String orgTreeCode) {
            this.orgTreeCode = orgTreeCode;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPhoneExt() {
            return phoneExt;
        }

        public void setPhoneExt(String phoneExt) {
            this.phoneExt = phoneExt;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public long getUpdateAt() {
            return updateAt;
        }

        public void setUpdateAt(long updateAt) {
            this.updateAt = updateAt;
        }
    }
}
