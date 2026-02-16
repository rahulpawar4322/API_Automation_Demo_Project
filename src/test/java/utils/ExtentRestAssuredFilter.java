package utils;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.response.Response;

public class ExtentRestAssuredFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification req,
                           FilterableResponseSpecification res,
                           FilterContext ctx) {

        ExtentTestManager.getTest().info(
                 "URL -> " + req.getURI());
        
        ExtentTestManager.getTest().info("Request Payload : ");
        if (req.getBody() != null) {
            ExtentTestManager.getTest().info(
                    MarkupHelper.createCodeBlock(
                            req.getBody().toString(),
                            CodeLanguage.JSON));
        }

        Response response = ctx.next(req, res);

        ExtentTestManager.getTest().info("Status Code : " + response.getStatusCode());

        ExtentTestManager.getTest().info("Response Payload : ");
        
        ExtentTestManager.getTest().info(
                MarkupHelper.createCodeBlock(
                        response.asPrettyString(),
                        CodeLanguage.JSON));

        return response;
    }
}
