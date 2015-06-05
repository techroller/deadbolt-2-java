/*
 * Copyright 2012 Steve Chaloner
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
package be.objectify.deadbolt.java.actions;

import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.JavaDeadboltAnalyzer;
import be.objectify.deadbolt.java.cache.HandlerCache;
import be.objectify.deadbolt.java.cache.SubjectCache;
import play.libs.F;
import play.mvc.Http;
import play.mvc.Result;

/**
 * Convenience class for checking if an qction has already been authorised before applying the restrictions.
 *
 * @author Steve Chaloner (steve@objectify.be)
 */
public abstract class AbstractRestrictiveAction<T> extends AbstractDeadboltAction<T>
{
    public AbstractRestrictiveAction(final JavaDeadboltAnalyzer analyzer,
                                     final SubjectCache subjectCache,
                                     final HandlerCache handlerCache)
    {
        super(analyzer,
              subjectCache,
              handlerCache);
    }

    @Override
    public F.Promise<Result> execute(final Http.Context ctx) throws Throwable
    {
        final F.Promise<Result> result;
        if (isActionAuthorised(ctx))
        {
            result = delegate.call(ctx);
        }
        else
        {
            final DeadboltHandler deadboltHandler = getDeadboltHandler(getHandlerKey());
            result = preAuth(true,
                             ctx,
                             deadboltHandler)
                    .flatMap(option -> option.map(F.Promise::pure)
                                             .orElseGet(() -> applyRestriction(ctx,
                                                                               deadboltHandler)));
        }
        return result;
    }

    /**
     * Get the key of a specific DeadboltHandler instance.
     *
     * @return a key.  May be null.
     */
    public abstract String getHandlerKey();

    public abstract F.Promise<Result> applyRestriction(Http.Context ctx,
                                                       DeadboltHandler deadboltHandler);
}