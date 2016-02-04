$(document).ready(function() { // on dom ready
    var cy = cytoscape({
        container: document.getElementById('cy'),
        style: cytoscape.stylesheet()
            .selector('node')
            .css({
                'content': 'data(name)',
                'text-valign': 'center',
                'color': 'white',
                'text-outline-width': 2,
                'text-outline-color': '#888'
            })
            .selector('edge')
            .css({
                'target-arrow-shape': 'triangle'
            })
            .selector(':selected')
            .css({
                'background-color': 'black',
                'line-color': 'black',
                'target-arrow-color': 'black',
                'source-arrow-color': 'black'
            })
            .selector('.faded')
            .css({
                'opacity': 0.25,
                'text-opacity': 0
            }),

        //elements: {
        //    nodes: [{
        //        data: {
        //            id: 'j',
        //            name: 'Jerry'
        //        }
        //    }, {
        //        data: {
        //            id: 'e',
        //            name: 'Elaine'
        //        }
        //    }, {
        //        data: {
        //            id: 'k',
        //            name: 'Kramer'
        //        }
        //    }, {
        //        data: {
        //            id: 'g',
        //            name: 'George'
        //        }
        //    }],
        //    edges: [{
        //        data: {
        //            source: 'j',
        //            target: 'e'
        //        }
        //    }, {
        //        data: {
        //            source: 'j',
        //            target: 'k'
        //        }
        //    }, {
        //        data: {
        //            source: 'j',
        //            target: 'g'
        //        }
        //    }, {
        //        data: {
        //            source: 'e',
        //            target: 'j'
        //        }
        //    }, {
        //        data: {
        //            source: 'e',
        //            target: 'k'
        //        }
        //    }, {
        //        data: {
        //            source: 'k',
        //            target: 'j'
        //        }
        //    }, {
        //        data: {
        //            source: 'k',
        //            target: 'e'
        //        }
        //    }, {
        //        data: {
        //            source: 'k',
        //            target: 'g'
        //        }
        //    }, {
        //        data: {
        //            source: 'g',
        //            target: 'j'
        //        }
        //    }]
        //},

        //ready: function() {
        //    window.cy = this;
        //
        //    // giddy up...
        //
        //    cy.elements().unselectify();
        //
        //    cy.on('tap', 'node', function(e) {
        //        var node = e.cyTarget;
        //        var neighborhood = node.neighborhood().add(node);
        //
        //        cy.elements().addClass('faded');
        //        neighborhood.removeClass('faded');
        //    });
        //
        //    cy.on('tap', function(e) {
        //        if (e.cyTarget === cy) {
        //            cy.elements().removeClass('faded');
        //        }
        //    });
        //}

    });
    cy.on('tap', 'node', function(e) {
        var node = e.cyTarget;
        var neighborhood = node.neighborhood().add(node);

        cy.elements().addClass('faded');
        neighborhood.removeClass('faded');
    });

    cy.on('tap', function(e) {
        if (e.cyTarget === cy) {
            cy.elements().removeClass('faded');
        }
    });

    $.getJSON("http://localhost:8080/TextAdventure/rest/quest/getData/123")
        .success(function(data) {
            console.log("graph data fetched from service");
            cy.add(data);
        })
        .error(function() {console.log("error on fetching graph data from service")});
}); // on dom ready

