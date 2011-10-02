<%--
    Document   : leadership
    Created on : 1 Feb, 2011, 11:45:45 AM
    Author     : bhaskar
--%>

<%@include file="/jsp/include.jsp"%>

<div id="leadership" class="profileContent roundedCorner" style="border: 1px solid #1F3533;">
    <logic:present name="employeeList" scope="application">
        <div id="leadershipHeader" class="profileHeader roundedCorner">
            Leadership
        </div>
        <br/>
        <div id="leadershipContent" class="leadershipContent">
            <logic:iterate id="employee" name="employeeList">
                <div id="leaderName_${employee.employeeId}">
                    <span class="empName">
                        ${employee.firstName}
                        <c:if test="${employee.middleName != null}">
                            ${employee.middleName}
                        </c:if>
                        ${employee.lastName}
                    </span>
                    <br/>
                    <span class="designation">
                    	${employee.designationTO.designationName}
                    </span>
                </div>
                <div id="leaderInfo_${employee.employeeId}" class="leaderInfo">
                    ${employee.ldto.leadershipDesc}
                </div>
                <br/>
            </logic:iterate>
        </div>
    </logic:present>
    <logic:notPresent name="employeeList" scope="application">
        Data not found. Please try again later.
    </logic:notPresent>
</div>