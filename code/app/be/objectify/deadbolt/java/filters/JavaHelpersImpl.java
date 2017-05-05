package be.objectify.deadbolt.java.filters;

import play.api.Configuration;
import play.api.Environment;
import play.api.http.FileMimeTypes;
import play.api.http.HttpConfiguration;
import play.api.i18n.Langs;
import play.api.i18n.MessagesApi;
import play.api.mvc.Cookie;
import play.api.mvc.Cookies;
import play.api.mvc.Request;
import play.api.mvc.RequestHeader;
import play.api.mvc.Result;
import play.core.j.JavaContextComponents;
import play.core.j.JavaHelpers;
import play.core.j.JavaHelpers$class;
import play.mvc.Http;
import scala.Function1;
import scala.Option;
import scala.Tuple2;
import scala.collection.Seq;
import scala.collection.immutable.Map;
import scala.concurrent.Future;

import java.net.URI;
import java.util.concurrent.CompletionStage;

public class JavaHelpersImpl implements JavaHelpers {
    @Override
    public Cookie cookieToScalaCookie(Http.Cookie c) {
        return JavaHelpers$class.cookieToScalaCookie(this, c);
    }

    @Override
    public Seq<Cookie> cookiesToScalaCookies(Iterable<Http.Cookie> cookies) {
        return JavaHelpers$class.cookiesToScalaCookies(this, cookies);
    }

    @Override
    public Http.Cookies cookiesToJavaCookies(Cookies cookies) {
        return (Http.Cookies) JavaHelpers$class.cookiesToJavaCookies(this, cookies);
    }

    @Override
    public Cookies mergeNewCookie(Cookies cookies, Cookie newCookie) {
        return JavaHelpers$class.mergeNewCookie(this, cookies, newCookie);
    }

    @Override
    public <A, B> Map<A, B> javaMapToImmutableScalaMap(java.util.Map<A, B> m) {
        return JavaHelpers$class.javaMapToImmutableScalaMap(this, m);
    }

    @Override
    public Seq<Tuple2<String, String>> javaMapOfArraysToScalaSeqOfPairs(java.util.Map<String, String[]> m) {
        return JavaHelpers$class.javaMapOfArraysToScalaSeqOfPairs(this, m);
    }

    @Override
    public java.util.Map<String, String[]> scalaMapOfSeqsToJavaMapOfArrays(Map<String, Seq<String>> m) {
        return JavaHelpers$class.scalaMapOfSeqsToJavaMapOfArrays(this, m);
    }

    @Override
    public <A> Request<A> updateRequestWithUri(Request<A> req, URI parsedUri) {
        return JavaHelpers$class.updateRequestWithUri(this, req, parsedUri);
    }

    @Override
    public Result createResult(Http.Context javaContext, play.mvc.Result javaResult) {
        return JavaHelpers$class.createResult(this, javaContext, javaResult);
    }

    @Override
    public Http.Context createJavaContext(RequestHeader req, JavaContextComponents components) {
        return JavaHelpers$class.createJavaContext(this, req, components);
    }

    @Override
    public Http.Context createJavaContext(Request<Http.RequestBody> req, JavaContextComponents components) {
        return JavaHelpers$class.createJavaContext(this, req, components);
    }

    @Override
    public JavaContextComponents createContextComponents() {
        return JavaHelpers$class.createContextComponents(this);
    }

    @Override
    public JavaContextComponents createContextComponents(Configuration configuration, Environment env) {
        return JavaHelpers$class.createContextComponents(this, configuration, env);
    }

    @Override
    public JavaContextComponents createContextComponents(MessagesApi messagesApi, Langs langs, FileMimeTypes fileMimeTypes, HttpConfiguration httpConfiguration) {
        return JavaHelpers$class.createContextComponents(this, messagesApi, langs, fileMimeTypes, httpConfiguration);
    }

    @Override
    public Option<Future<Result>> invokeWithContextOpt(RequestHeader request, JavaContextComponents components, Function1<Http.Request, CompletionStage<play.mvc.Result>> f) {
        return JavaHelpers$class.invokeWithContextOpt(this, request, components, f);
    }

    @Override
    public Future<Result> invokeWithContext(RequestHeader request, JavaContextComponents components, Function1<Http.Request, CompletionStage<play.mvc.Result>> f) {
        return JavaHelpers$class.invokeWithContext(this, request, components, f);
    }

    @Override
    public <A> A withContext(RequestHeader request, JavaContextComponents components, Function1<Http.Context, A> block) {
        return (A)JavaHelpers$class.withContext(this, request, components, block);
    }
}
