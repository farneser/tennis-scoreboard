function goUpOneLevel() {
    const currentPath = window.location.pathname;

    const pathSegments = currentPath.split('/');

    pathSegments.pop();

    window.location.href = pathSegments.join('/');
}
