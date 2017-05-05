/*
 * Copyright 2010-2016 Steve Chaloner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package be.objectify.deadbolt.java.cache;

import play.cache.SyncCacheApi;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.regex.Pattern;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
@Singleton
public class DefaultPatternCache implements PatternCache
{
    private final SyncCacheApi cache;

    @Inject
    public DefaultPatternCache(final SyncCacheApi cache)
    {
        this.cache = cache;
    }

    @Override
    public Pattern apply(final String patternValue)
    {
        return cache.getOrElseUpdate("Deadbolt." + patternValue,
                                     () -> Pattern.compile(patternValue));
    }
}
