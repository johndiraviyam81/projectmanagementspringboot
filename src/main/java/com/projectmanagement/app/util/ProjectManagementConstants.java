package com.projectmanagement.app.util;

/**
 * The Class ProjectManagementConstants.
 */
public final class ProjectManagementConstants {
	
	/**
	 * Instantiates a new project management constants.
	 */
	private ProjectManagementConstants() {
	}

	/** The Constant URL_PROJECT_Service. */
	public static final String URL_PROJECT_Service = "/projects";
	
	/** The Constant URL_PROJECT_getAllProjects. */
	public static final String URL_PROJECT_getAllProjects = "/allprojects";
	
	/** The Constant URL_PROJECT_searchAllProjects. */
	public static final String URL_PROJECT_searchAllProjects = "/search";
	
	/** The Constant URL_PROJECT_addProject. */
	public static final String URL_PROJECT_addProject = "/add";
	
	/** The Constant URL_PROJECT_deleteGetProject. */
	public static final String URL_PROJECT_deleteGetProject = "/project/{projectId}";
	
	/** The Constant URL_PROJECT_update. */
	public static final String URL_PROJECT_update = "/project";

	/** The Constant URL_TASK_Service. */
	public static final String URL_TASK_Service = "/tasks";
	
	/** The Constant URL_TASK_getAllTasks. */
	public static final String URL_TASK_getAllTasks = "/alltasks";
	
	/** The Constant URL_TASK_getAllParentTasks. */
	public static final String URL_TASK_getAllParentTasks = "/allparenttasks";
	
	/** The Constant URL_TASK_searchAllTasks. */
	public static final String URL_TASK_searchAllTasks = "/search";
	
	/** The Constant URL_TASK_addTask. */
	public static final String URL_TASK_addTask = "/add";
	
	/** The Constant URL_TASK_deleteGetTask. */
	public static final String URL_TASK_deleteGetTask = "/task/{taskId}";
	
	/** The Constant URL_TASK_update. */
	public static final String URL_TASK_update = "/task";
	
	/** The Constant URL_TASK_Parent_Task. */
	public static final String URL_TASK_Parent_Task = "/parent/{parentId}";
	
	/** The Constant URL_TASK_Parent_Task_Test. */
	public static final String URL_TASK_Parent_Task_Test = "/parent";

	/** The Constant URL_USER_Service. */
	public static final String URL_USER_Service = "/users";
	
	/** The Constant URL_USER_getAllUsers. */
	public static final String URL_USER_getAllUsers = "/allusers";
	
	/** The Constant URL_USER_searchAllUsers. */
	public static final String URL_USER_searchAllUsers = "/searchusers";
	
	/** The Constant URL_USER_addUser. */
	public static final String URL_USER_addUser = "/add";
	
	/** The Constant URL_USER_deleteGetUser. */
	public static final String URL_USER_deleteGetUser = "/user/{userId}";
	
	/** The Constant URL_USER_update. */
	public static final String URL_USER_update = "/user";

	/** The Constant PROJECT_Update_msgSuccess. */
	public static final String PROJECT_Update_msgSuccess = "Project is updated successfully";
	
	/** The Constant PROJECT_Update_msgFailure. */
	public static final String PROJECT_Update_msgFailure = "Error has been occured while updating project";

	/** The Constant PROJECT_Get_msgSuccess. */
	public static final String PROJECT_Get_msgSuccess = "Project is fetched successfully";
	
	/** The Constant PROJECT_Get_msgFailure. */
	public static final String PROJECT_Get_msgFailure = "Error has been occured while retrieving project";

	/** The Constant PROJECT_Delete_msgSuccess. */
	public static final String PROJECT_Delete_msgSuccess = "Project is deleted successfully along with related tasks";
	
	/** The Constant PROJECT_Delete_msgFailure. */
	public static final String PROJECT_Delete_msgFailure = "Project is not deleted successfully as user is referenced in users or tasks";

	/** The Constant PROJECT_Add_msgSuccess. */
	public static final String PROJECT_Add_msgSuccess = "Project is added successfully";
	
	/** The Constant PROJECT_Add_msgFailure. */
	public static final String PROJECT_Add_msgFailure = "Error has been occured while creating project";

	/** The Constant TASK_Delete_msgSuccess. */
	public static final String TASK_Delete_msgSuccess = "Task is deleted successfully";
	
	/** The Constant TASK_Delete_msgFailure. */
	public static final String TASK_Delete_msgFailure = "Task is not deleted successfully";

	/** The Constant TASK_Get_msgSuccess. */
	public static final String TASK_Get_msgSuccess = "Task is fetched successfully";
	
	/** The Constant TASK_Get_msgFailure. */
	public static final String TASK_Get_msgFailure = "Error has been occured while fetching task";

	/** The Constant TASK_Update_msgSuccess. */
	public static final String TASK_Update_msgSuccess = "Task is updated successfully";
	
	/** The Constant TASK_Update_msgFailure. */
	public static final String TASK_Update_msgFailure = "Error has been occured while updating task";

	/** The Constant TASK_Add_msgSuccess. */
	public static final String TASK_Add_msgSuccess = "Task is added successfully";
	
	/** The Constant TASK_Add_msgFailure. */
	public static final String TASK_Add_msgFailure = "Error has been occured while creating task";

	/** The Constant USER_Delete_msgSuccess. */
	public static final String USER_Delete_msgSuccess = "User is deleted successfully";
	
	/** The Constant USER_Delete_msgFailure. */
	public static final String USER_Delete_msgFailure = "User is not deleted successfully as user is referenced in projects or tasks";

	/** The Constant USER_Get_msgSuccess. */
	public static final String USER_Get_msgSuccess = "User is received successfully";
	
	/** The Constant USER_Get_msgFailure. */
	public static final String USER_Get_msgFailure = "Error has been occured while fetching user";

	/** The Constant USER_Add_msgSuccess. */
	public static final String USER_Add_msgSuccess = "User is added successfully";
	
	/** The Constant USER_Add_msgFailure. */
	public static final String USER_Add_msgFailure = "Error has been occured while creating user";

	/** The Constant USER_Update_msgSuccess. */
	public static final String USER_Update_msgSuccess = "User is updated successfully";
	
	/** The Constant USER_Update_msgFailure. */
	public static final String USER_Update_msgFailure = "Error has been occured while updating user";

}
