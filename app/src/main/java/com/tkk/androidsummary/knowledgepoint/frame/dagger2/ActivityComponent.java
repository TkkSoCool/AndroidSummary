package com.tkk.androidsummary.knowledgepoint.frame.dagger2;

import dagger.Component;

/**
 * Created  on 2018-05-28
 *
 * @author 唐开阔
 * @describe
 */
@Component(modules = {ActivityModule.class})
public interface ActivityComponent {
    void  inject(Dagger2Activity activity);
}
