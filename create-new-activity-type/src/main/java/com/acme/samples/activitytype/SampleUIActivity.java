package com.acme.samples.activitytype;
import org.exoplatform.webui.core.lifecycle.UIFormLifecycle;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.social.webui.activity.BaseUIActivity;
@ComponentConfig(
lifecycle = UIFormLifecycle.class,
template = "classpath:groovy/com/acme/samples/SampleUIActivity.gtmpl"
)
public class SampleUIActivity extends BaseUIActivity {
}