/*
 Map plugin v0.1 for Highcharts

 (c) 2011-2013 Torstein Hønsi

 License: www.highcharts.com/license
*/
(function (g) {
    function x(a, b, c) {
        for (var d = 4, e = []; d--;) e[d] = Math.round(b.rgba[d] + (a.rgba[d] - b.rgba[d]) * (1 - c));
        return "rgba(" + e.join(",") + ")"
    }
    var r = g.Axis,
        y = g.Chart,
        s = g.Point,
        z = g.Pointer,
        l = g.each,
        v = g.extend,
        p = g.merge,
        n = g.pick,
        A = g.numberFormat,
        B = g.getOptions(),
        k = g.seriesTypes,
        q = B.plotOptions,
        t = g.wrap,
        u = g.Color,
        w = function () {};
    B.mapNavigation = {
        buttonOptions: {
            align: "right",
            verticalAlign: "bottom",
            x: 0,
            width: 18,
            height: 18,
            style: {
                fontSize: "15px",
                fontWeight: "bold",
                textAlign: "center"
            }
        },
        buttons: {
            zoomIn: {
                onclick: function () {
                    this.mapZoom(0.5);
                },
                text: "+",
                y: -32
            },
            zoomOut: {
                onclick: function () {
                    this.mapZoom(2);
                },
                text: "-",
                y: 0
            }
        }
    };
    g.splitPath = function (a) {
        var b, a = a.replace(/([A-Za-z])/g, " $1 "),
            a = a.replace(/^\s*/, "").replace(/\s*$/, ""),
            a = a.split(/[ ,]+/);
        for (b = 0; b < a.length; b++) {
        	/[a-zA-Z]/.test(a[b]) || (a[b] = parseFloat(a[b]))
        };
        return a
    };
    g.maps = {};
    t(r.prototype, "getSeriesExtremes", function (a) {
        var b = this.isXAxis,
            c, d, e = [];
        l(this.series, function (a, b) {
            if (a.useMapGeometry) e[b] = a.xData, a.xData = []
        });
        a.call(this);
        c = n(this.dataMin, Number.MAX_VALUE);
        d = n(this.dataMax,
            Number.MIN_VALUE);
        l(this.series, function (a, i) {
            if (a.useMapGeometry) c = Math.min(c, a[b ? "minX" : "minY"]), d = Math.max(d, a[b ? "maxX" : "maxY"]), a.xData =
                    e[i]
        });
        this.dataMin = c;
        this.dataMax = d
    });
    t(r.prototype, "setAxisTranslation", function (a) {
        var b = this.chart,
            c = b.plotWidth / b.plotHeight,
            d = this.isXAxis,
            e = b.xAxis[0];
        a.call(this);
        if (b.options.chart.type === "map" && !d && e.transA !== void 0) this.transA = e.transA = Math.min(this.transA,
                e.transA), a = (e.max - e.min) / (this.max - this.min), e = a > c ? this : e, c = (e.max - e.min) * e.transA,
                e.minPixelPadding =
                (e.len - c) / 2
    });
    t(y.prototype, "render", function (a) {
        var b = this,
            c = b.options.mapNavigation;
        a.call(b);
        b.renderMapNavigation();
        c.zoomOnDoubleClick && g.addEvent(b.container, "dblclick", function (a) {
            b.pointer.onContainerDblClick(a)
        });
        c.zoomOnMouseWheel && g.addEvent(b.container, document.onmousewheel === void 0 ? "DOMMouseScroll" :
            "mousewheel", function (a) {
            b.pointer.onContainerMouseWheel(a)
        })
    });
    v(z.prototype, {
        onContainerDblClick: function (a) {
            var b = this.chart,
                a = this.normalize(a);
            b.isInsidePlot(a.chartX - b.plotLeft, a.chartY -
                b.plotTop) && b.mapZoom(0.5, b.xAxis[0].toValue(a.chartX), b.yAxis[0].toValue(a.chartY))
        },
        onContainerMouseWheel: function (a) {
            var b = this.chart,
                c, a = this.normalize(a);
            c = a.detail || -(a.wheelDelta / 120);
            b.isInsidePlot(a.chartX - b.plotLeft, a.chartY - b.plotTop) && b.mapZoom(c > 0 ? 2 : 0.5, b.xAxis[0].toValue(
                a.chartX), b.yAxis[0].toValue(a.chartY))
        }
    });
    t(z.prototype, "init", function (a, b, c) {
        a.call(this, b, c);
        if (c.mapNavigation.enableTouchZoom) this.pinchX = this.pinchHor = this.pinchY = this.pinchVert = !0
    });
    v(y.prototype, {
        renderMapNavigation: function () {
            var a =
                this,
                b = this.options.mapNavigation,
                c = b.buttons,
                d, e, f, i = function () {
                    this.handler.call(a)
                };
            if (b.enableButtons) for (d in c) if (c.hasOwnProperty(d)) f = p(b.buttonOptions, c[d]), e = a.renderer.button(
                            f.text, 0, 0, i).attr({
                            width: f.width,
                            height: f.height
                        }).css(f.style).add(), e.handler = f.onclick, e.align(v(f, {
                            width: e.width,
                            height: e.height
                        }), null, "spacingBox")
        },
        fitToBox: function (a, b) {
            l([["x", "width"], ["y", "height"]], function (c) {
                var d = c[0],
                    c = c[1];
                a[d] + a[c] > b[d] + b[c] && (a[c] > b[c] ? (a[c] = b[c], a[d] = b[d]) : a[d] = b[d] + b[c] - a[c]);
                a[c] > b[c] && (a[c] = b[c]);
                a[d] < b[d] && (a[d] = b[d])
            });
            return a
        },
        mapZoom: function (a, b, c) {
            if (!this.isMapZooming) {
                var d = this,
                    e = d.xAxis[0],
                    f = e.max - e.min,
                    i = n(b, e.min + f / 2),
                    b = f * a,
                    f = d.yAxis[0],
                    h = f.max - f.min,
                    c = n(c, f.min + h / 2);
                a *= h;
                i -= b / 2;
                h = c - a / 2;
                c = n(d.options.chart.animation, !0);
                b = d.fitToBox({
                    x: i,
                    y: h,
                    width: b,
                    height: a
                }, {
                    x: e.dataMin,
                    y: f.dataMin,
                    width: e.dataMax - e.dataMin,
                    height: f.dataMax - f.dataMin
                });
                e.setExtremes(b.x, b.x + b.width, !1);
                f.setExtremes(b.y, b.y + b.height, !1);
                if (e = c ? c.duration || 500 : 0) d.isMapZooming = !0, setTimeout(function () {
                        d.isMapZooming = !1
                    }, e);
                d.redraw()
            }
        }
    });
    q.map = p(q.scatter, {
        animation: !1,
        nullColor: "#F8F8F8",
        borderColor: "silver",
        borderWidth: 1,
        marker: null,
        stickyTracking: !1,
        dataLabels: {
            verticalAlign: "middle"
        },
        turboThreshold: 0,
        tooltip: {
            followPointer: !0,
            pointFormat: "{point.name}: {point.y}<br/>"
        },
        states: {
            normal: {
                animation: !0
            }
        }
    });
    r = g.extendClass(s, {
        applyOptions: function (a, b) {
            var c = s.prototype.applyOptions.call(this, a, b);
            if (c.path && typeof c.path === "string") c.path = c.options.path = g.splitPath(c.path);
            return c
        },
        onMouseOver: function () {
            clearTimeout(this.colorInterval);
            s.prototype.onMouseOver.call(this)
        },
        onMouseOut: function () {
            var a = this,
                b = +new Date,
                c = u(a.options.color),
                d = u(a.pointAttr.hover.fill),
                e = a.series.options.states.normal.animation,
                f = e && (e.duration || 500);
            if (f && c.rgba.length === 4 && d.rgba.length === 4) delete a.pointAttr[""].fill, clearTimeout(a.colorInterval),
                    a.colorInterval = setInterval(function () {
                    var e = (new Date - b) / f,
                        h = a.graphic;
                    e > 1 && (e = 1);
                    h && h.attr("fill", x(d, c, e));
                    e >= 1 && clearTimeout(a.colorInterval)
                }, 13);
            s.prototype.onMouseOut.call(a)
        }
    });
    k.map = g.extendClass(k.scatter, {
        type: "map",
        pointAttrToOptions: {
            stroke: "borderColor",
            "stroke-width": "borderWidth",
            fill: "color"
        },
        colorKey: "y",
        pointClass: r,
        trackerGroups: ["group", "markerGroup", "dataLabelsGroup"],
        getSymbol: w,
        supportsDrilldown: !0,
        getExtremesFromAll: !0,
        useMapGeometry: !0,
        init: function (a) {
            var b = this,
                c = a.options.legend.valueDecimals,
                d = [],
                e, f, i, h, j, o, m;
            o = a.options.legend.layout === "horizontal";
            g.Series.prototype.init.apply(this, arguments);
            j = b.options.colorRange;
            if (h = b.options.valueRanges) l(h, function (a) {
                    f = a.from;
                    i = a.to;
                    e =
                        "";
                    f === void 0 ? e = "< " : i === void 0 && (e = "> ");
                    f !== void 0 && (e += A(f, c));
                    f !== void 0 && i !== void 0 && (e += " - ");
                    i !== void 0 && (e += A(i, c));
                    d.push(g.extend({
                        chart: b.chart,
                        name: e,
                        options: {},
                        drawLegendSymbol: k.area.prototype.drawLegendSymbol,
                        visible: !0,
                        setState: function () {},
                        setVisible: function () {}
                    }, a))
                }), b.legendItems = d;
            else if (j) f = j.from, i = j.to, h = j.fromLabel, j = j.toLabel, m = o ? [0, 0, 1, 0] : [0, 1, 0, 0], o ||
                    (o = h, h = j, j = o), o = {
                    linearGradient: {
                        x1: m[0],
                        y1: m[1],
                        x2: m[2],
                        y2: m[3]
                    },
                    stops: [[0, f], [1, i]]
            }, d = [{
                    chart: b.chart,
                    options: {},
                    fromLabel: h,
                    toLabel: j,
                    color: o,
                    drawLegendSymbol: this.drawLegendSymbolGradient,
                    visible: !0,
                    setState: function () {},
                    setVisible: function () {}
                }], b.legendItems = d
        },
        drawLegendSymbol: k.area.prototype.drawLegendSymbol,
        drawLegendSymbolGradient: function (a, b) {
            var c = a.options.symbolPadding,
                d = n(a.options.padding, 8),
                e, f, i = this.chart.renderer.fontMetrics(a.options.itemStyle.fontSize).h,
                h = a.options.layout === "horizontal",
                j;
            j = n(a.options.rectangleLength, 200);
            h ? (e = -(c / 2), f = 0) : (e = -j + a.baseline - c / 2, f = d + i);
            b.fromText = this.chart.renderer.text(b.fromLabel,
                f, e).attr({
                zIndex: 2
            }).add(b.legendGroup);
            f = b.fromText.getBBox();
            b.legendSymbol = this.chart.renderer.rect(h ? f.x + f.width + c : f.x - i - c, f.y, h ? j : i, h ? i : j, 2)
                .attr({
                zIndex: 1
            }).add(b.legendGroup);
            j = b.legendSymbol.getBBox();
            b.toText = this.chart.renderer.text(b.toLabel, j.x + j.width + c, h ? e : j.y + j.height - c).attr({
                zIndex: 2
            }).add(b.legendGroup);
            e = b.toText.getBBox();
            h ? (a.offsetWidth = f.width + j.width + e.width + c * 2 + d, a.itemY = i + d) : (a.offsetWidth = Math.max(
                f.width, e.width) + c + j.width + d, a.itemY = j.height + d, a.itemX = c)
        },
        getBox: function (a) {
            var b =
                Number.MIN_VALUE,
                c = Number.MAX_VALUE,
                d = Number.MIN_VALUE,
                e = Number.MAX_VALUE;
            l(a || this.options.data, function (a) {
                for (var i = a.path, h = i.length, j = !1, g = Number.MIN_VALUE, m = Number.MAX_VALUE, k = Number.MIN_VALUE,
                        l = Number.MAX_VALUE; h--;) typeof i[h] === "number" && !isNaN(i[h]) && (j ? (g = Math.max(g, i[
                        h]), m = Math.min(m, i[h])) : (k = Math.max(k, i[h]), l = Math.min(l, i[h])), j = !j);
                a._maxX = g;
                a._minX = m;
                a._maxY = k;
                a._minY = l;
                b = Math.max(b, g);
                c = Math.min(c, m);
                d = Math.max(d, k);
                e = Math.min(e, l)
            });
            this.minY = e;
            this.maxY = d;
            this.minX = c;
            this.maxX =
                b
        },
        translatePath: function (a) {
            var b = !1,
                c = this.xAxis,
                d = this.yAxis,
                e, a = [].concat(a);
            for (e = a.length; e--;) typeof a[e] === "number" && (a[e] = b ? Math.round(c.translate(a[e])) : Math.round(
                    d.len - d.translate(a[e])), b = !b);
            return a
        },
        setData: function () {
            g.Series.prototype.setData.apply(this, arguments);
            this.getBox()
        },
        translate: function () {
            var a = this,
                b = Number.MAX_VALUE,
                c = Number.MIN_VALUE;
            a.generatePoints();
            l(a.data, function (d) {
                d.shapeType = "path";
                d.shapeArgs = {
                    d: a.translatePath(d.path)
                };
                if (typeof d.y === "number") if (d.y > c) c =
                            d.y;
                    else if (d.y < b) b = d.y
            });
            a.translateColors(b, c)
        },
        translateColors: function (a, b) {
            var c = this.options,
                d = c.valueRanges,
                e = c.colorRange,
                f = this.colorKey,
                i, h;
            e && (i = u(e.from), h = u(e.to));
            l(this.data, function (g) {
                var k = g[f],
                    m, l, n;
                if (d) for (n = d.length; n--;) {
                        if (m = d[n], i = m.from, h = m.to, (i === void 0 || k >= i) && (h === void 0 || k <= h)) {
                            l = m.color;
                            break;
                        }
                } else e && k !== void 0 && (m = 1 - (b - k) / (b - a), l = k === null ? c.nullColor : x(i, h, m)); if (
                    l) g.color = null, g.options.color = l
            })
        },
        drawGraph: w,
        drawDataLabels: w,
        drawPoints: function () {
            var a = this.xAxis,
                b = this.yAxis,
                c = this.colorKey;
            l(this.data, function (a) {
                a.plotY = 1;
                if (a[c] === null) a[c] = 0, a.isNull = !0
            });
            k.column.prototype.drawPoints.apply(this);
            l(this.data, function (d) {
                var e = d.dataLabels,
                    f = a.toPixels(d._minX, !0),
                    g = a.toPixels(d._maxX, !0),
                    h = b.toPixels(d._minY, !0),
                    j = b.toPixels(d._maxY, !0);
                d.plotX = Math.round(f + (g - f) * n(e && e.anchorX, 0.5));
                d.plotY = Math.round(h + (j - h) * n(e && e.anchorY, 0.5));
                d.isNull && (d[c] = null)
            });
            g.Series.prototype.drawDataLabels.call(this)
        },
        animateDrilldown: function (a) {
            var b = this.chart.plotBox,
                c = this.chart.drilldownLevels[this.chart.drilldownLevels.length - 1],
                d = c.bBox,
                e = this.chart.options.drilldown.animation;
            if (!a) a = Math.min(d.width / b.width, d.height / b.height), c.shapeArgs = {
                    scaleX: a,
                    scaleY: a,
                    translateX: d.x,
                    translateY: d.y
            }, l(this.points, function (a) {
                a.graphic.attr(c.shapeArgs).animate({
                    scaleX: 1,
                    scaleY: 1,
                    translateX: 0,
                    translateY: 0
                }, e)
            }), delete this.animate
        },
        animateDrillupFrom: function (a) {
            k.column.prototype.animateDrillupFrom.call(this, a)
        },
        animateDrillupTo: function (a) {
            k.column.prototype.animateDrillupTo.call(this,
                a)
        }
    });
    q.mapline = p(q.map, {
        lineWidth: 1,
        backgroundColor: "none"
    });
    k.mapline = g.extendClass(k.map, {
        type: "mapline",
        pointAttrToOptions: {
            stroke: "color",
            "stroke-width": "lineWidth",
            fill: "backgroundColor"
        },
        drawLegendSymbol: k.line.prototype.drawLegendSymbol
    });
    q.mappoint = p(q.scatter, {
        dataLabels: {
            enabled: !0,
            format: "{point.name}",
            color: "black",
            style: {
                textShadow: "0 0 5px white"
            }
        }
    });
    k.mappoint = g.extendClass(k.scatter, {
        type: "mappoint"
    });
    g.Map = function (a, b) {
        var c = {
            endOnTick: !1,
            gridLineWidth: 0,
            labels: {
                enabled: !1
            },
            lineWidth: 0,
            minPadding: 0,
            maxPadding: 0,
            startOnTick: !1,
            tickWidth: 0,
            title: null
        }, d;
        d = a.series;
        a.series = null;
        a = p({
            chart: {
                type: "map",
                panning: "xy"
            },
            xAxis: c,
            yAxis: p(c, {
                reversed: !0
            })
        }, a, {
            chart: {
                inverted: !1
            }
        });
        a.series = d;
        return new g.Chart(a, b);
    }
})(Highcharts);