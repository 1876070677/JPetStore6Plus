<%--

       Copyright 2010-2022 the original author or authors.

       Licensed under the Apache License, Version 2.0 (the "License");
       you may not use this file except in compliance with the License.
       You may obtain a copy of the License at

          https://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing, software
       distributed under the License is distributed on an "AS IS" BASIS,
       WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
       See the License for the specific language governing permissions and
       limitations under the License.

--%>
<%@ include file="../common/IncludeTop.jsp"%>

<%--<div id="BackLink"><stripes:link--%>
<%--        beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"--%>
<%--        event="viewProduct">--%>
<%--    <stripes:param name="productId" value="${actionBean.product.productId}" />--%>
<%--    Return to ${actionBean.product.productId}--%>
<%--</stripes:link></div>--%>

<div id="Catalog">

    <stripes:form beanclass="org.mybatis.jpetstore.web.actions.DiaryActionBean"
                  focus="">
        <table>
            <tr>
                <th>Image</th>
                <th>Title</th>
                <th>Content</th>
            </tr>
            <tr>
                <td><stripes:file name="petImage" accept=".jpg,.png,.jpeg"/></td>
                <td><stripes:text name="diary.title"/></td>
                <td><stripes:text name="diary.content"/></td>
            </tr>
        </table>
        <stripes:submit name="addDiary" value="Submit" />
    </stripes:form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>



