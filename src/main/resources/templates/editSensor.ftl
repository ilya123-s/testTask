<#import "parts/common.ftl" as c>

<@c.page "none">
    <div style="margin-left: 0%; margin-bottom: 110px">
        <form method="post" action="/admin/show">
            <h3 style="color: #1e90ff; margin-left: 20%">                 Edit sensor</h3>
            <table  style="margin-top: 2px">
                <thead align="center">
                <tr>
                    <th scope="col" bgcolor="#1e90ff">Name</th>
                    <th scope="col" bgcolor="#1e90ff">Model</th>
                    <th scope="col" bgcolor="#1e90ff">Type</th>
                    <th scope="col" bgcolor="#1e90ff">Range from</th>
                    <th scope="col" bgcolor="#1e90ff">Range to</th>
                    <th scope="col" bgcolor="#1e90ff">Unit</th>
                    <th scope="col" bgcolor="#1e90ff">Location</th>
                </tr>
                <tr align="center">
                    <th><input class="text-form" type="text" name="name" value="${sensor.name}"></th>
                    <th><input class="text-form" type="text" name="model" value="${sensor.model}"></th>
                    <th><select class="text-form" name="choiceTypeSen" class="selectpicker" data-style="btn-info">
                            <#list typesSensors as typeSen>
                                <option value="${typeSen.name}">${typeSen.name}</option>
                            </#list>
                        </select></th>
                    <th><input class="text-form" type="number" name="rangeFrom" value="${sensor.rangeFrom}"></th>
                    <th><input class="text-form" type="number" name="rangeTo" value="${sensor.rangeTo}"></th>
                    <th><select class="text-form" name="choiceTypeSen" class="selectpicker" data-style="btn-info">
                            <#list units as unit>
                                <option value="${unit.name}">${unit.name}</option>
                            </#list>
                        </select></th>
                    <th><input class="text-form" type="text" name="location" value="${sensor.location}"></th>
                </tr>
                </thead>
            </table>
            <input type="hidden" value="${sensor.id}" name="id">
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <button style="margin-left: 35%" type="submit" class="gradient-button">Edit</button>
        </form>
    </div>
</@c.page>