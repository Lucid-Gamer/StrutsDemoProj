CREATE TABLE `com_ldgr_dbrd_menu_mst` (
  `id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `menu_name` varchar(30) NOT NULL,
  `menu_tab_id` varchar(50) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT 0,
  `maker_cd` int(11) DEFAULT NULL,
  `maker_dt` date DEFAULT curdate(),
  `author_cd` int(11) DEFAULT NULL,
  `author_dt` date DEFAULT curdate(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `menu_id` (`menu_id`)
) 

=======================================================================================================================================================================

CREATE TABLE `com_ldgr_dbrd_menu_items_mst` (
  `id` int(11) NOT NULL,
  `menu_item_name` varchar(30) DEFAULT NULL,
  `menu_item_name_id` varchar(50) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT 0,
  `maker_cd` int(11) DEFAULT NULL,
  `maker_dt` date DEFAULT curdate(),
  `author_cd` int(11) DEFAULT NULL,
  `author_dt` date DEFAULT curdate(),
  PRIMARY KEY (`id`)
) 

=======================================================================================================================================================================

ALTER TABLE com_ldgr_dbrd_menu_items_mst 
ADD COLUMN item_menu_action VARCHAR(255) 
GENERATED ALWAYS AS (
    CONCAT(
        '/', 
        LOWER(SUBSTRING_INDEX(menu_item_name, ' ', 1)),  -- First word in lowercase
        UPPER(SUBSTRING(menu_item_name, LENGTH(SUBSTRING_INDEX(menu_item_name, ' ', 1)) + 2, 1)),  -- Capitalize first character of the second word
        LOWER(SUBSTRING(menu_item_name, LENGTH(SUBSTRING_INDEX(menu_item_name, ' ', 1)) + 3))  -- Rest of the second word in lowercase
    )
) STORED;

========================================================================================================================================================================


<!-- Modal for Editing User -->
<div class="modal fade" id="editUserModal" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Edit User</h5>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <s:form id="editUserForm" action="updateUser" method="post" theme="simple">
                    <s:hidden name="user.id" id="userId" />
                    <div class="form-group">
                        <label>Username:</label>
                        <s:textfield name="user.username" id="username" cssClass="form-control" />
                        <s:fielderror fieldName="user.username" cssClass="text-danger" />
                    </div>
                    <div class="form-group">
                        <label>Email:</label>
                        <s:textfield name="user.email" id="email" cssClass="form-control" />
                        <s:fielderror fieldName="user.email" cssClass="text-danger" />
                    </div>
                    <button type="button" class="btn btn-primary" onclick="updateUser()">Save</button>
                </s:form>
            </div>
        </div>
    </div>
</div>

<script>
// Fetch user data and populate modal
function editUser(userId) {
    $.ajax({
        url: 'fetchUserById',
        type: 'GET',
        data: { userId: userId },
        success: function(response) {
            $('#userId').val(response.user.id);
            $('#username').val(response.user.username);
            $('#email').val(response.user.email);
            $('#editUserModal').modal('show');
        },
        error: function() {
            alert('Failed to fetch user data');
        }
    });
}

// Update user
function updateUser() {
    $.ajax({
        url: 'updateUser',
        type: 'POST',
        data: $('#editUserForm').serialize(),
        success: function() {
            alert('User updated successfully');
            $('#editUserModal').modal('hide');
            location.reload();
        },
        error: function() {
            alert('Failed to update user');
        }
    });
}
</script>

==================================================================================================================================================================