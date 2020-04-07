package ai.infrrd.customization.trailto.service;

import ai.infrrd.customization.trailto.vo.ScanDataResponseVO;

import java.util.Optional;

public interface ApigatewayService
{
    Optional<ScanDataResponseVO> apigatewayGetCall( String xcci, String scanId );
}
