/*
 * Copyright 2010-2017 Steve Chaloner
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
package be.objectify.deadbolt.java.composite;

import be.objectify.deadbolt.java.ConstraintLogic;
import be.objectify.deadbolt.java.ConstraintPoint;
import be.objectify.deadbolt.java.DeadboltHandler;
import play.mvc.Http;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.function.BiFunction;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public class DynamicConstraint implements Constraint
{
    private final String name;
    private final Optional<String> meta;
    private final Optional<String> content;
    private final ConstraintLogic constraintLogic;

    public DynamicConstraint(final String name,
                             final Optional<String> meta,
                             final Optional<String> content,
                             final ConstraintLogic constraintLogic)
    {
        this.name = name;
        this.meta = meta;
        this.content = content;
        this.constraintLogic = constraintLogic;
    }

    @Override
    public CompletionStage<Boolean> test(final Http.Context context,
                                         final DeadboltHandler handler,
                                         final Executor executor,
                                         final Optional<String> globalMetaData,
                                         final BiFunction<Optional<String>, Optional<String>, Optional<String>> metaFn)
    {
        return constraintLogic.dynamic(context,
                                       handler,
                                       content,
                                       name,
                                       metaFn.apply(globalMetaData, meta),
                                       ctx -> CompletableFuture.completedFuture(Boolean.TRUE),
                                       (ctx, dh, cnt) -> CompletableFuture.completedFuture(Boolean.FALSE),
                                       ConstraintPoint.CONTROLLER);
    }
}
