const Foo = { template: "<div>foo</div>" };
const Bar = { template: "<div>bar</div>" };

export default {
    linkActiveClass: "active",
    routes: [{ path: "/foo", component: Foo }, { path: "/bar", component: Bar }]
};
