package com.atg.openssp.core.entry;

import com.atg.openssp.common.demand.ParamValue;
import com.atg.openssp.common.demand.TestParamValue;
import com.atg.openssp.common.exception.EmptyCacheException;
import com.atg.openssp.common.exception.RequestException;
import com.atg.openssp.core.cache.type.SiteDataCache;

import javax.servlet.http.HttpServletRequest;

public class TestEntryValidatorHandler  extends EntryValidatorHandler {
    @Override
    public ParamValue validateEntryParams(HttpServletRequest request) throws RequestException {
        final TestParamValue pm = new TestParamValue();
        pm.setIsTest(request.getParameter("test"));

        // Note:
        // You may define your individual parameter or payloadto work with.
        // Neither the "ParamValue" - object nor the list of params may fit to your requirements out of the box.

        // geo data could be solved by a geo lookup service and ipaddress

        final String siteid = request.getParameter("site");
        try {
            pm.setSite(SiteDataCache.instance.get(siteid));
        } catch (final EmptyCacheException e) {
            throw new RequestException(e.getMessage());
        }

        // pm.setDomain(checkValue(request.getParameter("domain"), ERROR_CODE.E906, "Domain"));
        // pm.setH(checkValue(request.getParameter("h"), ERROR_CODE.E906, "Height"));
        // pm.setW(checkValue(request.getParameter("w"), ERROR_CODE.E906, "Width"));
        // pm.setMimes(convertMimes(request.getParameter("mimes")));
        // pm.setPage(checkValue(request.getParameter("page"), pm.getDomain()));
        // pm.setStartdelay(Integer.valueOf(checkValue(request.getParameter("sd"), "0")));
        // pm.setProtocols(convertProtocolValues(request.getParameter("prot")));

        pm.setIpAddress(request.getRemoteAddr());

        return pm;
    }
}
