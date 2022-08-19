<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page "none">
    <#if isAdmin>
        <div>
            <form method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <h3 style="color: #1e90ff;     margin-left: 35%;">                           Add new sensor</h3>
                <input style="margin-left: 15%" class="text-form" type="text" name="name" placeholder="Name">
                <input class="text-form"  type="text" name="model" placeholder="Model">
                <select class="text-form" name="choiceTypeSen" class="selectpicker" data-style="btn-info">
                    <#list typesSensors as typeSen>
                        <option value="${typeSen.name}">${typeSen.name}</option>
                    </#list>
                </select>
                <input class="text-form"  type="number" name="rangeFrom" placeholder="Range from">
                <input class="text-form"  type="number" name="rangeTo" placeholder="Range to">
                <select class="text-form" name="choiceUnit" class="selectpicker" data-style="btn-info">
                    <#list units as unit>
                        <option value="${unit.name}">${unit.name}</option>
                    </#list>
                </select>
                <input class="text-form"  type="text" name="location" placeholder="Location"><br>
                <button style="margin-left: 43%" class="gradient-button" type="submit">Add</button>
            </form>
        </div>
    </#if>
    <#if isAdmin>
        <form method="post" action="/admin/filter">
    <#else>
        <form method="post" action="/user/filter">
    </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <h3 style="color: #1e90ff; margin-left: 35%">Search for sensors by name and model</h3>
        <input style="margin-left: 42%" class="text-form"  type="text" name="filterName" placeholder="Name"><br>
        <input style="margin-left: 42%" class="text-form"  type="text" name="filterModel" placeholder="Model"><br>
        <button  style="margin-left: 44.5%" class="gradient-button" type="submit">Search</button>
    </form>

    <div class="wrapper">
        <form method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <div style="padding-top: 57px">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th style="border: 1px solid black; text-align: center; font-size: 15px" scope="col" bgcolor="#1e90ff">Name</th>
                        <th style="border: 1px solid black; text-align: center; font-size: 15px" scope="col" bgcolor="#1e90ff">Model</th>
                        <th style="border: 1px solid black; text-align: center; font-size: 15px" scope="col" bgcolor="#1e90ff">Type</th>
                        <th style="border: 1px solid black; text-align: center; font-size: 15px" scope="col" bgcolor="#1e90ff">Range from</th>
                        <th style="border: 1px solid black; text-align: center; font-size: 15px" scope="col" bgcolor="#1e90ff">Range To</th>
                        <th style="border: 1px solid black; text-align: center; font-size: 15px" scope="col" bgcolor="#1e90ff">Unit</th>
                        <th style="border: 1px solid black; text-align: center; font-size: 15px" scope="col" bgcolor="#1e90ff">Location</th>
                        <#if isAdmin>
                        <th style="border: 1px solid black; text-align: center; font-size: 15px" scope="col" bgcolor="#1e90ff" width="100">Edit</th>
                        <th style="border: 1px solid black; text-align: center; font-size: 15px" scope="col" bgcolor="#1e90ff" width="100">Delete</th>
                        </#if>
                    </tr>
                    </thead>
                    <tbody>
                    <#list sensors as sensor>
                        <tr>
                            <td style="border: 1px solid black"><strong>${sensor.name}</strong></td>
                            <td style="border: 1px solid black"><strong>${sensor.model}</strong></td>
                            <td style="border: 1px solid black"><strong>${sensor.type}</strong></td>
                            <td style="border: 1px solid black"><strong>${sensor.rangeFrom}</strong></td>
                            <td style="border: 1px solid black"><strong>${sensor.rangeTo}</strong></td>
                            <td style="border: 1px solid black"><strong>${sensor.unit}</strong></td>
                            <td style="border: 1px solid black"><strong>${sensor.location}</strong></td>
                            <#if isAdmin>
                            <td style="border: 1px solid black">
                                <form method="get" action="/admin/${sensor.id}">
                                    <button type="submit" class="gradient-button-grey">Edit</button>
                                </form>
                            </td>
                            <td style="border: 1px solid black">
                                <form method="post" action="/admin/deleteSensor">
                                    <input type="hidden" value="${sensor.id}" name="sensorId">
                                    <input type="hidden" value="${_csrf.token}" name="_csrf">
                                    <button class="gradient-button-red" type="submit">Delete</button>
                                </form>
                            </td>
                            </#if>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </form>
    </div>
</@c.page>
<script>
     validSensor(${flagResult})
</script>