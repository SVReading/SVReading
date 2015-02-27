package edu.gatech.cats.svreading.View;

import edu.gatech.cats.svreading.R;

public class MainActivity extends ARActivity {

    /**
     * The nav draw toggle that handles listening to/opening and closing the nav drawer
     */

    /**
     * The draw layout holding the nav drawer
     */

    private  final String WORLD_PATH = "index.html";
    private  final String WIKITUDE_SDK_KEY = "CSU+/8HIRGJh0bAqaYTpkp6qcnyfV5yEnOIFVEE/5T1N8k84kMJz8puHjhA+V+r7xGnizLw1CVt5/qYuvexwfmZeFwsOyuxetU5AC9F1s5/hzLKDNJOn0GBq8OhfpLnd3Ne3GO+b0ZOJDLmA75x/wWZXvmVtRBMblI94O2oBWmdTYWx0ZWRfX78Y6eenrSsW2xQDO7MkObj6INrJiZOQduDwZ0LtT5Ry85jU0FqBo+SkRvQOJYwYHzDBL00ca4zbDTGeqwgzDTG52hbWDCjvcZ6AH/BIZPxQUm4hNaRV6HRpLXs7AgWfEIpZ9W9r7o3oQq7gxGvkVhEkggE3XMEvFrWai9NXDgCHSeSx3MjVUxjIvHRj81ZsqEhrzq8jlDsO4525BIK+/zJOQfZc+M91apCtlrenLo7+Md1zK/+bgweB9pPXODj88aCA0anDzzUyurxPYMeLo7q6UDx90mLrxYW+AbL4F1PBTQAPaYiSyr+DUrqgGq5e832Bf5dIz/lhSNasE8rrUAe0hX0AbXq2fbk3ED8Ehz2YqCyiN+BW1RJGjbIl8ksYWhtTQ9z12Vkczwdd2dXBPk31B6EBHRFhpbVBiTLgyzxmv4HD9yRfdEGOjTSvCEECmb9yeAle9NnV1eH7i23O44kr30DHk6jBOGERaPT7vgrNMAxiGErTIBE=";


    public String getARchitectWorldPath() {
        return WORLD_PATH;
    }

    @Override
    public String getActivityTitle() {
        return getString(R.string.app_name);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public int getArchitectViewId() {
        return R.id.architectView;
    }

    @Override
    public String getWikitudeSDKLicenseKey() {
        return WIKITUDE_SDK_KEY;
    }
}